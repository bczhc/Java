package com.zhc.u.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class n {
    String api(long qqn) {
        String r = "";
        try {
            URL url = new URL("http://users.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg?uins=" + qqn);
            InputStream in = url.openStream();
            InputStreamReader isr = new InputStreamReader(in, "gbk");
            BufferedReader bufr = new BufferedReader(isr);
            r = bufr.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    String[] apiSplit(long qqn) {
        String str = api(qqn);
        String reg = "\"";
        String[] r = new String[2], rr = str.split(reg);
        r[0] = rr[1];
        r[1] = rr[5];
        return r;
    }
}
