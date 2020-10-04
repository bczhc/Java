package pers.zhc.u.crawler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import pers.zhc.u.common.Documents;
import pers.zhc.u.common.ReadIS;
import pers.zhc.u.util.Connection;

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

    private static class Result {
        public String text, html;
    }

    private static Map<String, String> headerMap;

    public static void main(String[] args) throws IOException {
        File file = new File("./result");
        OutputStream os = new FileOutputStream(file);
        String header = "accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\n" +
                "accept-encoding: gzip, deflate, br\n" +
                "accept-language: en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7\n" +
                "cache-control: no-cache\n" +
                "cookie: zzpaneluin=; zzpanelkey=; pgv_pvi=200938496; pgv_si=s6973862912; pgv_pvid=3585738994; pgv_info=ssid=s3568835412; ptui_loginuin=1109236592; uin=o1109236592; skey=@vhImZNXwU; RK=O2Aswv62O5; ptcz=7d8350c7c5bd03cd75b1adc6f10b58af7331a46376bebcab54dbca90a6857676; p_uin=o1109236592; pt4_token=lUz0sO5ntfBXz7JokafyqCV2MN0i6iThVqgh5VzSMKk_; p_skey=z8EB*9z31Awzwum1XF2pp8Leka5tp6DvTaubYC9dbhA_; Loading=Yes; qz_screen=1536x864; 1109236592_todaycount=0; 1109236592_totalcount=2131; rv2=804DAE8E161709D8E08B5E86744360CBBE4F1886287EFCEA1D; property20=69F51022D41586DFFAF6B3BA2A5138689783114700228586EACA114E4DC9F52E037AB8CA6ACF2D29; QZ_FE_WEBP_SUPPORT=1; __Q_w_s__QZN_TodoMsgCnt=1; cpu_performance_v8=2; __Q_w_s_hat_seed=1\n" +
                "pragma: no-cache\n" +
                "referer: https://user.qzone.qq.com/proxy/domain/qzs.qq.com/qzone/newblog/blogcanvas.html\n" +
                "sec-fetch-dest: iframe\n" +
                "sec-fetch-mode: navigate\n" +
                "sec-fetch-site: same-origin\n" +
                "upgrade-insecure-requests: 1\n" +
                "user-agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36";
        headerMap = Connection.stringParams2Map(header);

        String query = "hostUin: 1109236592\n" +
                "uin: 1109236592\n" +
                "blogType: 0\n" +
                "cateName: \n" +
                "cateHex: \n" +
                "statYear: \n" +
                "reqInfo: 1\n" +
                "pos: 15\n" +
                "num: 15\n" +
                "sortType: 0\n" +
                "absType: 0\n" +
                "source: 0\n" +
                "rand: 0.4310602855887069\n" +
                "ref: qzone\n" +
                "g_tk: 1399649976\n" +
                "verbose: 0\n" +
                "iNotice: 0\n" +
                "inCharset: utf-8\n" +
                "outCharset: utf-8\n" +
                "format: jsonp\n" +
                "qzonetoken: 616d8d6829ea0208bde002cea4cf69c8372e486c4571929cbef2e9c9e782d2152b166eeee0dab0e472\n" +
                "g_tk: 1399649976";
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
}
