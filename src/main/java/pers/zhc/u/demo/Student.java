package pers.zhc.u.demo;

import pers.zhc.u.Base128;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Arrays;

public class Student {
    public static void main(String[] args) throws IOException {
        URL u = new URL("http://218.4.55.34/ShareDisk/2018/1/201811015/" + URLEncoder.encode("文档1", "UTF-8") + ".docx");
        URLConnection urlC = u.openConnection();
        InputStream s = urlC.getInputStream();
        OutputStream r = Base128.encode(s);
        System.out.println(Arrays.toString(((ByteArrayOutputStream) r).toByteArray()));
    }
}