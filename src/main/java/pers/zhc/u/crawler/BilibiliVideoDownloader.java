package pers.zhc.u.crawler;

import org.json.JSONArray;
import org.json.JSONObject;
import pers.zhc.u.util.Connection;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BilibiliVideoDownloader {
    private static final String header =
            "accept: */*\n" +
                    "origin: https://www.bilibili.com\n" +
                    "referer: https://www.bilibili.com/\n" +
                    "user-agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36";
    private static final Map<String, String> headerMap = Connection.stringParams2Map(header);

    public static void main(String[] args) throws IOException {
        File downloadDir = new File("/home/zhc/bilidl");
        downloadDir.mkdirs();
        ExecutorService es = Executors.newCachedThreadPool();

        String bvid = "BV17x411w7KC";

        URL partUrl = new URL(String.format("https://api.bilibili.com/x/player/pagelist?bvid=%s&jsonp=jsonp", bvid));
        URLConnection connection = Connection.get(partUrl, null, headerMap);
        String partInfo = Connection.getString(connection);

        JSONArray partJsonArray = new JSONObject(partInfo).getJSONArray("data");
        for (Object o : partJsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            long cid = jsonObject.getLong("cid");
            int page = jsonObject.getInt("page");
            String part = jsonObject.getString("part");
            System.out.println(part + " " + cid);

            es.execute(() -> {
                try {
                    download(getVideoURL(bvid, cid), new File(downloadDir, part + " " + page));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }
    }

    private static URL getVideoURL(String bvid, long cid) throws IOException {
        URL url = new URL(String.format("https://api.bilibili.com/x/player/playurl?bvid=%s&cid=%d&qn=122", bvid, cid));
        URLConnection connection = Connection.get(url, null, headerMap);
        String s = Connection.getString(connection);
        return new URL(new JSONObject(s).getJSONObject("data").getJSONArray("durl").getJSONObject(0).getString("url"));
    }

    private static void download(URL url, OutputStream os) throws IOException {
        URLConnection connection = Connection.get(url, null, headerMap);
        InputStream is = connection.getInputStream();
        long contentLength = connection.getContentLengthLong();
        int read;
        long totalRead = 0;
        byte[] buffer = new byte[4096];
        int i = 0;
        while ((read = is.read(buffer)) > 0) {
            os.write(buffer, 0, read);
            os.flush();
            totalRead += read;
            if (i % 128 == 0) {
                System.out.println((((float) totalRead) / ((float) contentLength) * 100F) + "%");
            }
            ++i;
        }
        is.close();
        System.out.println("100%");
    }

    private static void download(URL url, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        download(url, os);
        os.close();
    }
}
