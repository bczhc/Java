
package com.zhc.u.demo;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class r {

    public static void main(String[] args) throws Exception {
        r r = new r();
        FileOutputStream fos = new FileOutputStream("pow7,1000.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
    }


    private BigDecimal factorial(BigDecimal n) {
        BigDecimal bd = new BigDecimal(1);
        for (long i = 2; i <= n.longValue(); i++) {
            bd = bd.multiply(new BigDecimal(i));
        }
        return bd;
    }

    public BigDecimal power(BigDecimal base, BigDecimal exponent) {
        BigDecimal bd = new BigDecimal(base.toString());
        for (long i = 0; i < exponent.longValue() - 1; i++) {
            bd = bd.multiply(base);
        }
        return bd;
    }
}