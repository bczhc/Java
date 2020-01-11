/*
package z;


import pers.zhc.u.FileU;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class WebPush {
    public static class ZHC {
        public static ZHC o = new ZHC();

        public static void main(String[] args) throws IOException {
            URLConnection connection = new URL("http://235m82e811.imwork.net/i.zhc?m=p&t=f").openConnection();
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.write(new byte[]{1, 2, 3});
            dataOutputStream.flush();
            connection.connect();
            dataOutputStream.close();
        }

        public void wPush(String name, String content, String url) throws IOException {
            URL u;
            if (url == null) {
                u = new URL("http://103.46.128.43:19353?n=" + URLEncoder.encode(name, "UTF-8") + "&c=" + URLEncoder.encode(content, "UTF-8"));
            } else
                u = new URL(FileU.o.StrToUrl(url).toString() + "?n=" + URLEncoder.encode(name, "UTF-8") + "&c=" + URLEncoder.encode(content, "UTF-8"));
            u.openStream();
        }
    }
}
*/
