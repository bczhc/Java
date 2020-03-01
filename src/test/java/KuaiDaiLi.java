import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pers.zhc.u.common.ReadIS;
import pers.zhc.u.util.Connection;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class KuaiDaiLi {

    public static void main(String[] args) throws IOException {
        String userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36";
        File file = new File("/home/zhc/code/code/java/src/test/proxy.txt");
        OutputStream os = new FileOutputStream(file, false);
        int n = '\n';
        for (int i = 1; i < 3317; i++) {
            URL url = new URL("https://www.kuaidaili.com/free/inha/" + i + "/");
            Map<String, String> p = new HashMap<>();
            p.put("User-Agent", userAgent);
            URLConnection connection = Connection.get(url, null, p);
            InputStream is = connection.getInputStream();
            StringBuilder sb = new StringBuilder();
            new ReadIS(is, StandardCharsets.UTF_8).read(sb::append);
            String s = sb.toString();
            Document parse = Jsoup.parse(s);
            Elements list = parse.getElementById("list").child(1).child(1).children();
            for (Element element : list) {
                String ip = element.child(0).text();
                String port = element.child(1).text();
                String text = ip + ":" + port;
                os.write(text.getBytes());
                os.write(n);
                os.flush();
                System.out.println(i + "\t" + text);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        os.close();
    }
}