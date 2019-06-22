package Z;


import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

public class WebPush {
    public static class ZHC {
        public static ZHC o = new ZHC();

        public static void main(String[] args) {

        }

        public void wPush(String name, String content, String url) throws IOException {
            URL u;
            if (url == null) {
                u = new URL("http://103.46.128.43:19353?n=" + URLEncoder.encode(name, "UTF-8") + "&c=" + URLEncoder.encode(content, "UTF-8"));
            } else
                u = new URL(com.zhc.u.u_File.o.StrToUrl(url).toString() + "?n=" + URLEncoder.encode(name, "UTF-8") + "&c=" + URLEncoder.encode(content, "UTF-8"));
            u.openStream();
        }
    }
}
