package com.zhc.u.demo.JNI;

public class b {
    public static void main(String[] args) {
        System.load("C:\\zhc\\code\\C99\\b.dll");
        System.out.println("new b().f0() = " + new b().f0());
    }

    private native int f0();
}