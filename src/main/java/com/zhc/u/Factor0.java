package com.zhc.u;

import java.util.Scanner;

/**
 * 一次编写的分解质因数 by zhc
 */
public class Factor0 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数字");
        System.out.println(prime(sc.nextLong()));
        main(args);
    }

    private static StringBuilder prime(long x) {
        if (x < 0) {
            System.out.println("去你的。。。");
            System.exit(0);
        }
        int a = 0;
        StringBuilder r = new StringBuilder();
        r.append(x).append(" = ");
        if (x <= 2) {
            r.append(x);
        } else {
            do {
                for (int i = 2; true; i++) {
                    if (isPrime(i) && (x % i == 0)) {
                        if (i == x) {
                            r.append(i);
                            a = 1;
                            break;
                        } else {
                            r.append(i).append(" × ");
                            x /= i;
                            break;
                        }
                    }
                }
            } while (!isPrime(x));
            if (a != 1) r.append(x);
        }
        return r;
    }

    private static boolean isPrime(long n) {
        boolean r = true;
        if (n == 1) r = false;
        for (int j = 2; j < n; j++) {
            if (n % j == 0) {
                r = false;
                break;
            }
        }
        return r;
    }
}
