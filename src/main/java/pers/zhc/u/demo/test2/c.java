package pers.zhc.u.demo.test2;

import pers.zhc.u.FileU;

import java.io.*;

public class c extends FileU {
    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream("C:\\zhc\\f\\s2\\A");
        File f = new File("C:\\zhc\\f\\s2\\b");
        System.out.println(f.createNewFile());
        OutputStream os = new FileOutputStream(f);
        byte[] b = new byte[4096];
        while (true) {
            if (is.read(b) != -1) {
                os.write(b);
            } else break;
        }
        os.flush();
        os.close();
        is.close();
    }
}