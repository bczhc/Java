package com.zhc.u.demo;
//import java.lang.Math;

public class b {
    public static void main(String[] args) {
        for (int i = 'A'; i < 'a'; i++) {
            byte[] b = new byte[1];
            b[0] = (byte) i;
            System.out.println("" + new String(b));
        }
    }
}