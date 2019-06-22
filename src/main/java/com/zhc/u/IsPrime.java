package com.zhc.u;

import java.util.Scanner;

public class IsPrime {
    public static void main(String[] args) {
        IsPrime o = new IsPrime();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数字：");
        long startTime = System.currentTimeMillis();
        System.out.println(o.isPrm(sc.nextLong()));
        long endTime = System.currentTimeMillis();
        System.out.println("共用时间：" + (endTime - startTime));
        main(args);
    }

    Boolean isPrm(Long n) {
        boolean r = true;
        for (long i = 2L; i <= Math.sqrt(n); i++) {
            if (n % i == 0) r = false;
        }
        return r;
    }
}