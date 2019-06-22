package com.zhc.u.demo;

import java.util.Scanner;

public class g {
    private static boolean isPrime(Long i) {
        boolean isPrime = true;
        //除到i的平方根就可以判断
        for (int j = 2; j <= Math.sqrt(i); j++) {
            if (i % j == 0)
                isPrime = false;
        }
        return isPrime;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        StringBuilder out = new StringBuilder(n + "=");
        if (isPrime(n)) {
            out.append(n);
        } else {
            while (n != 1) {
                for (int j = 2; j <= n; j++) {
                    //对最后一个进行特殊处理
                    if (j == n) {
                        n = 1;
                        out.append(j);
                        break;
                    }
                    if (n % j == 0) {
                        n = n / j;
                        out.append(j).append("x");
                        break;
                    }
                }
            }
        }
        System.out.println(out);
        in.close();
    }

}
