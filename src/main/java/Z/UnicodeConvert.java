package Z;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class UnicodeConvert {
    public static void main(String[] args) throws IOException {
        long t1 = System.currentTimeMillis();
        UnicodeConvert o = new UnicodeConvert();
        File f = new File("/home/zhc/Documents/unicode__.txt");
        OutputStream os = new FileOutputStream(f);
        OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        for (int i = 0; i <= 0xffffff; i++) {
            if (i <= 0xFFFF) {
                String[] strings = new String[1];
                strings[0] = Integer.toHexString(i);
                String s = o.unicodeToString(strings);
                String format = String.format("%d %s %s", i, "\\u" + strings[0], s);
                if (i % 10000 == 0) System.out.println(format);
                bw.write(format + "\n");
                bw.flush();
                continue;
            }
            String s1 = Integer.toHexString(i);
            String s = o.f(s1);
            String[] strings = s.split("\\\\u");
            String[] ss = new String[2];
            ss[0] = strings[1];
            ss[1] = strings[2];
            String format = String.format("%d %s %s %s", i, s, s1, o.unicodeToString(ss));
            if (i % 10000 == 0) System.out.println(format);
            bw.write(format + "\n");
            bw.flush();
        }
        bw.close();
        osw.close();
        os.close();
        long t2 = System.currentTimeMillis();
        System.out.println("Time-taken: " + ((double) (t2 - t1)) / 1000);
    }

    private String f(String s) {
        int i = Integer.parseInt(s, 16) - 0x10000;
        int n1 = (i >> 10) + 0xD800, n2 = (i & 0b1111111111) + 0xDC00;
        return "\\u" + Integer.toHexString(n1).toUpperCase() + "\\u" + Integer.toHexString(n2).toUpperCase();
    }

    private String unicodeToString(String[] uS_hex) {
        StringBuilder sb = new StringBuilder();
        for (String uSHex : uS_hex) {
            sb.append((char) Integer.parseInt(uSHex, 16));
        }
        return sb.toString();
    }
}