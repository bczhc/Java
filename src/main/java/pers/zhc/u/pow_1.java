package pers.zhc.u;//
// Source code recreated from test1 .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class pow_1 {

    public static void main(String[] args) throws Exception {
        pow_1 o = new pow_1();
        FileOutputStream fos = new FileOutputStream("math.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        BigDecimal pow1 = o.power(new BigDecimal(8), new BigDecimal(9));
        System.out.println(pow1.toString());
        BigDecimal pow2 = o.power(new BigDecimal(7), new BigDecimal(pow1.doubleValue() / 10000));
        BigDecimal pow3 = o.power(pow2, new BigDecimal(50));
        bw.write(pow3.multiply(pow3).multiply(pow3).multiply(pow3).multiply(pow3).toString());
        bw.flush();
    }


    BigDecimal power(BigDecimal base, BigDecimal exponent) {
        BigDecimal bd = new BigDecimal(base.toString());

        for (int i = 0; (long) i < exponent.longValue() - 1L; ++i) {
            bd = bd.multiply(base);
        }

        return bd;
    }
}
