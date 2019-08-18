package pers.zhc.u.demo;

import java.io.*;
import java.net.*;

public class k {
    private static int n = 10000;

    public static void main(String[] args) {
        URL url;
        InputStream in;
        try {
            url = new URL("http://users.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg?uins=" + n);
            in = url.openStream();
            InputStreamReader isr = new InputStreamReader(in, "gbk");
            BufferedReader bufr = new BufferedReader(isr);
            String str = "";
            while (str != null) {
                str = bufr.readLine();
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        n++;
        main(args);
    }
}