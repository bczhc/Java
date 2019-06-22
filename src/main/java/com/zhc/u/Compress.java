package com.zhc.u;

import java.io.*;

public class Compress extends com.zhc.u.u_File {
    public static void main(String[] args) throws IOException {
        File f = new File("C:/zhc/f/a/大壮-魔鬼中的天使.mp3");
        InputStream is = new FileInputStream(f);
        OutputStream os = compress(is);
//        System.out.println(Arrays.toString(((ByteArrayOutputStream) os).toByteArray()));
    }

    public static OutputStream compress(InputStream in) throws IOException {
        ByteArrayOutputStream r = new ByteArrayOutputStream();
        byte[] b = new byte[1];
        byte c, t = 0;
        while (true) {
            if (in.read(b) != -1) {
                c = b[0];
                if (c == t) {
                    System.out.println(c);
                }
                t = c;
            } else break;
        }
        return r;
    }
}
