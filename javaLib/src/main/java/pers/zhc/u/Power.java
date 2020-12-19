package pers.zhc.u;


import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Power {
    private static Power o = new Power();

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\u8bf7\u4f9d\u6b21\u8f93\u5165\u5e95\u548c\u5e42\uff1a");
        BigDecimal base = new BigDecimal(sc.nextLong());
        BigDecimal exponent = new BigDecimal(sc.nextLong());
        File file = new File("./result");
        if (!file.exists()) System.out.println(file.mkdir());
        FileOutputStream fos = new FileOutputStream("./result/pow_1" + base + "," + exponent + ".txt");
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        long t0 = System.currentTimeMillis();
        System.out.println("\u6b63\u5728\u8ba1\u7b97...");
        BigDecimal result = o.power(base, exponent);
        long t1 = System.currentTimeMillis();
        double t = t1 - t0;
        bw.write(String.format("%s\r\n\r\n", result.toString()));
        bw.write(String.format("用时%sms", t));
        bw.flush();
        bw.close();
        System.out.println(result.toString());
        System.out.printf("用时%sms%n", t);
        System.out.println();
        main(args);
    }

    public BigDecimal power(int base, int exponent) {
        BigDecimal bd = BigDecimal.valueOf(base);
        BigDecimal r = BigDecimal.ONE;
        for (int i = 0; i < exponent; i++) {
            r = r.multiply(bd);
        }
        return r;
    }

    public BigDecimal power(BigDecimal base, BigDecimal exponent) {
        BigDecimal bd = new BigDecimal(base.toString());
        for (long i = 0; i < exponent.longValue() - 1; i++) {
            bd = bd.multiply(base);
        }
        return bd;
    }
}