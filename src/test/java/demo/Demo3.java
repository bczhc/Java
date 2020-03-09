package demo;

import com.itextpdf.text.DocumentException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import pers.zhc.u.ImagePDF;
import pers.zhc.u.common.ReadIS;
import pers.zhc.u.util.Connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo3 {
    @Test
    public void test() throws IOException {
        File dir = new File("/home/zhc/Downloads/教材电子书");
        if (!dir.exists()) {
            System.out.println("dir.mkdirs() = " + dir.mkdirs());
        }
        File jsonFile = new File("/home/zhc/code/code/java/src/test/coursesData.json");
        InputStream is = new FileInputStream(jsonFile);
        StringBuilder sb = new StringBuilder();
        new ReadIS(is, StandardCharsets.UTF_8).read(sb::append);
        is.close();
        JSONObject jsonObject = new JSONObject(sb.toString());
        JSONArray list = jsonObject.getJSONArray("list");
        ExecutorService es = Executors.newFixedThreadPool(24);
        CountDownLatch latch = new CountDownLatch(list.length());
        for (Object o1 : list) {
            es.execute(() -> {
                try {
                    JSONObject o = (JSONObject) o1;
                    String link = o.getString("link");
                    String bookName = o.getString("bookName");
                    File bookPDF = new File(dir, bookName + ".pdf");
                    if (link.charAt(link.length() - 1) == '/') {
                        link = link.substring(0, link.length() - 1);
                    }
                    ImagePDF imagePDF = new ImagePDF(new FileOutputStream(bookPDF));
                    int pageCount = getPageCount(link);
                    for (int i = 1; i <= pageCount; i++) {
                        URL imageURL = new URL(link + "/files/mobile/" + i + ".jpg");
                        try {
                            imagePDF.putImage(imageURL);
                            System.out.println(bookName + " " + i);
                        } catch (DocumentException e) {
                            e.printStackTrace();
                        }
                    }
                    imagePDF.close();
                    System.out.println("pageCount = " + pageCount);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }
        es.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done.");
    }

    private int getPageCount(String bookLink) {
        try {
            URL configJSURL = new URL(bookLink + "/mobile/javascript/config.js");
            URLConnection connection = Connection.get(configJSURL, null, null);
            InputStream inputStream = connection.getInputStream();
            String s = ReadIS.readToString(inputStream, StandardCharsets.UTF_8);
            Matcher matcher = Pattern.compile("totalPageCount=[0-9]*").matcher(s);
            if (matcher.find()) {
                String group = matcher.group(0);
                return Integer.parseInt(group.replace("totalPageCount=", ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
