package pers.zhc.u.demo;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class l {
    public static void main(String s) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("o.txt", true), StandardCharsets.UTF_8));
            try {
                bw.write(s + "\r\n");
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}