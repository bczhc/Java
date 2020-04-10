import pers.zhc.u.common.ReadIS;
import pers.zhc.u.util.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Test26 {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://127.0.0.1:8080/test.jsp");
        Map<String, String> properties = new HashMap<>();
        properties.put("Cookie", "");
        URLConnection connection = Connection.get(url, null, properties);
        String cookiesString = Connection.getCookiesString(connection);
        System.out.println("cookiesString = " + cookiesString);
        InputStream is = connection.getInputStream();
        new ReadIS(is, StandardCharsets.UTF_8).read(System.out::println);
    }
}
