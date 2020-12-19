package pers.zhc.u.crawler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import pers.zhc.u.common.Documents;
import pers.zhc.u.common.ReadIS;
import pers.zhc.u.util.Connection;
import pers.zhc.utils.MySQLite3;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.GZIPInputStream;

public class QzoneDiary {
    private static Map<String, String> headerMap;

    public static void main(String[] args) throws IOException {
        File file = new File("./result");
        OutputStream os = new FileOutputStream(file);
        String header = ":authority: user.qzone.qq.com\n" +
                ":method: GET\n" +
                ":path: /proxy/domain/b.qzone.qq.com/cgi-bin/blognew/get_abs?hostUin=1109236592&uin=1109236592&blogType=0&cateName=&cateHex=&statYear=2020&reqInfo=7&pos=0&num=15&sortType=0&source=0&rand=0.6875723943308512&ref=qzone&g_tk=659303272&verbose=1&qzonetoken=1065edccc3a1a69d457d2a9efcb7c04b523bb46d88331911f911e175905dc8d9a80e9feba54e3e83\n" +
                ":scheme: https\n" +
                "accept: */*\n" +
                "accept-encoding: gzip, deflate, br\n" +
                "accept-language: en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7\n" +
                "cache-control: no-cache\n" +
                "cookie: pgv_pvi=6927831040; pgv_pvid=6595637488; ptui_loginuin=1109236592; RK=z3Akhv6GP7; ptcz=99fe8b3f306432262f5b58f4cded757f4a4d95ec77e401b35dbc2de04964c724; qz_screen=1366x768; QZ_FE_WEBP_SUPPORT=1; zzpaneluin=; zzpanelkey=; pgv_si=s1123604480; pgv_info=ssid=s818670670; uin=o1109236592; skey=@fzG6bt6sx; p_uin=o1109236592; pt4_token=GdoouMreCRiI4VtHrdYYGh*B18bc21-Yn9hSnBrYjys_; p_skey=7Y3jkmoywddWx8EVTrfBtl0feuMnVTpdjabafUVnt3k_; Loading=Yes; 1109236592_todaycount=0; 1109236592_totalcount=2133; __Q_w_s__QZN_TodoMsgCnt=1; cpu_performance_v8=5\n" +
                "pragma: no-cache\n" +
                "referer: https://user.qzone.qq.com/proxy/domain/qzs.qq.com/qzone/app/blog/v6/bloglist.html\n" +
                "sec-ch-ua: \"Chromium\";v=\"86\", \"\\\"Not\\\\A;Brand\";v=\"99\", \"Google Chrome\";v=\"86\"\n" +
                "sec-ch-ua-mobile: ?0\n" +
                "sec-fetch-dest: script\n" +
                "sec-fetch-mode: no-cors\n" +
                "sec-fetch-site: same-origin\n" +
                "user-agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36";
        headerMap = Connection.stringParams2Map(header);

        String query = "hostUin: 1109236592\n" +
                "uin: 1109236592\n" +
                "blogType: 0\n" +
                "cateName: \n" +
                "cateHex: \n" +
                "statYear: 2020\n" +
                "reqInfo: 7\n" +
                "pos: 0\n" +
                "num: 15\n" +
                "sortType: 0\n" +
                "source: 0\n" +
                "rand: 0.6875723943308512\n" +
                "ref: qzone\n" +
                "g_tk: 659303272\n" +
                "verbose: 1\n" +
                "qzonetoken: 1065edccc3a1a69d457d2a9efcb7c04b523bb46d88331911f911e175905dc8d9a80e9feba54e3e83";
        Map<String, String> queryMap = Connection.stringParams2Map(query);
        queryMap.replace("rand", String.valueOf(Math.random()));
        queryMap.replace("g_tk", String.valueOf(QQFriendList.getToken(Connection.cookie2Map(headerMap.get("cookie")).get("p_skey"))));

        URL url = new URL("https://user.qzone.qq.com/proxy/domain/b.qzone.qq.com/cgi-bin/blognew/get_abs");
        String read = getContent(url, queryMap, headerMap, true, "UTF-8");
        int totalNum = getTotalNum(read);
//        queryMap.replace("num", "1");
        queryMap.replace("num", String.valueOf(totalNum));

        String get = getContent(url, queryMap, headerMap, true, "UTF-8");
        JSONArray resolved = resolve(get);
        os.write(resolved.toString().getBytes(StandardCharsets.UTF_8));
        os.flush();
        os.close();

    }

    private static int getTotalNum(String read) {
        read = read.substring(read.indexOf('{'), read.lastIndexOf('}') + 1);
        JSONObject jsonObject = new JSONObject(read);
        return jsonObject.getJSONObject("data").getInt("totalNum");
    }

    private static JSONArray resolve(String read) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        AtomicReference<JSONArray> r = new AtomicReference<>(new JSONArray());
        read = read.substring(read.indexOf('{'), read.lastIndexOf('}') + 1);
        JSONObject jsonObject = new JSONObject(read);
        JSONArray data = jsonObject.getJSONObject("data").getJSONArray("list");
        AtomicInteger i = new AtomicInteger();
        CountDownLatch latch = new CountDownLatch(data.length());
        for (Object o : data) {
            es.execute(() -> {
                JSONObject datum = (JSONObject) o;
                long blogId = datum.getLong("blogId");
                String pubTime = datum.getString("pubTime");
                String title = datum.getString("title");
                try {
                    Result detail = getDetail(blogId);
                    JSONObject diaryJSONObject = new JSONObject();
                    diaryJSONObject.put("pubTime", pubTime);
                    diaryJSONObject.put("title", title);
                    diaryJSONObject.put("text", detail.text);
                    diaryJSONObject.put("html", detail.html);
                    r.get().put(diaryJSONObject);
                    System.out.println(i.getAndIncrement());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }
        try {
            latch.await();
            es.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return r.get();
    }

    private static Result getDetail(long blogId) throws IOException {
        URL url = new URL("https://user.qzone.qq.com/proxy/domain/b.qzone.qq.com/cgi-bin/blognew/blog_output_data");
        String query = "uin: 1109236592\n" +
                "blogid: xxx\n" +
                "styledm: qzonestyle.gtimg.cn\n" +
                "imgdm: user.qzone.qq.com/proxy/domain/qzs.qq.com\n" +
                "bdm: b.qzone.qq.com\n" +
                "mode: 2\n" +
                "numperpage: 15\n" +
                "timestamp: xxx\n" +
                "dprefix: \n" +
                "blogseed: 0.6293441244805806\n" +
                "inCharset: gb2312\n" +
                "outCharset: gb2312\n" +
                "ref: qzone\n" +
                "g_iframeUser: 1\n" +
                "g_iframedescend: 1\n" +
                "entertime: 1601778996689\n" +
                "cdn_use_https: 1";
        Map<String, String> queryMap = Connection.stringParams2Map(query);
        long timestamp = System.currentTimeMillis();
        queryMap.replace("timestamp", String.valueOf(timestamp / 1000));
        queryMap.replace("entertime", String.valueOf(timestamp));
        queryMap.replace("blogid", String.valueOf(blogId));

        String content = getContent(url, queryMap, headerMap, true, "GB2312");
        Document document = Jsoup.parse(content);
        Element blogDetailDiv = document.getElementById("blogDetailDiv");
        Result result = new Result();
        result.html = blogDetailDiv.html();
        result.text = blogDetailDiv.text();
        return result;
    }

    private static String getContent(URL url, @Documents.Nullable Map<String, String> queryParams
            , Map<String, String> requestProperties, boolean gZipCompressed, String charset) {
        StringBuilder sb = new StringBuilder();
        try {
            URLConnection connection = Connection.get(url, queryParams, requestProperties);
            InputStream is = connection.getInputStream();
            if (gZipCompressed) {
                return gZipUncompress(is, charset);
            } else {
                new ReadIS(is, charset).read(sb::append);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static String gZipUncompress(InputStream is, String charset) throws UnsupportedEncodingException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            GZIPInputStream unGZip = new GZIPInputStream(is);
            byte[] buffer = new byte[256];
            int n;
            while ((n = unGZip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(out.toByteArray(), charset);
    }

    static class ToSqlite3 {
        public static void main(String[] args) throws IOException {
            File file = new File("/home/zhc/qzone-diary");
            InputStream is = new FileInputStream(file);
            String read = ReadIS.readToString(is, StandardCharsets.UTF_8);
            is.close();
            JSONArray jsonArray = new JSONArray(read);
            MySQLite3 db = MySQLite3.open("./qzone_diary_result.db");
            db.exec("DROP TABLE IF EXISTS qzone_diary");
            db.exec("CREATE TABLE IF NOT EXISTS qzone_diary (\n" +
                    "    publish_time TEXT NOT NULL,\n" +
                    "    html_data TEXT NOT NULL,\n" +
                    "    plain_text TEXT NOT NULL,\n" +
                    "    title TEXT NOT NULL\n" +
                    ")");
            db.exec("BEGIN TRANSACTION");
            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                String pubTime = jsonObject.getString("pubTime").replace("'", "''");
                String html = jsonObject.getString("html").replace("'", "''");
                String text = jsonObject.getString("text").replace("'", "''");
                String title = jsonObject.getString("title").replace("'", "''");
                db.exec(String.format("INSERT INTO qzone_diary VALUES('%s','%s','%s','%s')", pubTime, html, text, title));
            }
            db.exec("COMMIT");
            db.close();
            System.out.println("Done.");
        }
    }

    private static class Result {
        public String text, html;
    }
}
