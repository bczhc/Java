package com.zhc.u;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class pow_2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("math.txt");
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        BigDecimal bd = new BigDecimal(br.readLine());
        pow_1 o = new pow_1();
        BigDecimal bd1 = new BigDecimal(o.power(bd, new BigDecimal(50)).toString());
        FileOutputStream fos = new FileOutputStream("test1.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(bd1.toString());
    }
}
