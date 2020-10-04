package demo;

import org.junit.Test;
import pers.zhc.u.common.ReadIS;
import pers.zhc.u.util.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class Demo5 {
    @Test
    public void test() {
        try {
            URL url = new URL("http://idea.medeming.com/jihuoma/images/jihuoma.txt");
            URLConnection connection = Connection.get(url, null, null);
            InputStream is = connection.getInputStream();
            StringBuilder sb = new StringBuilder();
            new ReadIS(is, StandardCharsets.UTF_8).read(sb::append);
            is.close();
            String s = sb.toString();
            System.out.println("s = " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
