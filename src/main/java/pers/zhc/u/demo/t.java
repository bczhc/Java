package pers.zhc.u.demo;

import pers.zhc.u.common.Execute;
import pers.zhc.u.common.StrDic;

import java.io.IOException;

public class t {
    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                new StrDic(StrDic.Num, 16).Do((s, n) -> {
                    System.out.println(n);
                    try {
                        Execute.exec(new String[]{"fastboot", "oem", "unlock", s}, "GBK");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }).start();
        }
    }
}