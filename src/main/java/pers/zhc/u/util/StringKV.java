package pers.zhc.u.util;

import pers.zhc.u.YouDao;

import java.io.IOException;
import java.util.Scanner;

public class StringKV {
    public String key, value;

    public StringKV(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            String s = sc.nextLine();
            String hello = YouDao.translate(s, null, null);
            System.out.println("hello = " + hello);
            main(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
