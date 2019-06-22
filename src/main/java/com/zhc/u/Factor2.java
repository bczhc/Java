package com.zhc.u;

import java.util.Scanner;

/**
 * @author zhc
 */
public class Factor2 {
    private IsPrime isPrime = new IsPrime();

    /**
     * 比factor1只是加了个质数验证，验证比较大的质数是不需要等太久。
     * by zhc 2018.11.9 20:06
     * 调用isPrime.isPrm()。
     * 某些方面算法更优
     *
     * @param args args
     */
    public static void main(String[] args) {
        String r_;
        Factor2 l = new Factor2();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数字：");
        long n = sc.nextLong();
        long startTime = System.currentTimeMillis();
        r_ = l.factor(n);
        long endTime = System.currentTimeMillis();
        System.out.println("最终结果：" + r_);
        System.out.println("用时: " + (endTime - startTime) + "ms");
        System.out.println();
        System.out.println();
        main(args);
    }

    public String factor(long n) {
        String r;
        StringBuilder s = new StringBuilder();
        s.append(n).append("=");
        if (n > 2) {
            if (isPrime.isPrm(n)) {
                s.append(n);
            } else {
                while ((int) n != 1) {
                    for (int i = 2; i <= n; i++) {
                        if (n % i == 0) {
                            s.append(i).append("×");
                            n /= i;
                            break;
                        }
                    }
                }
            }
        } else if (n == 1) {
            s.append(1);
        } else if (n < 1) {
            System.exit(0);
        } else {
            s.append(2);
        }
        if ((s.substring(s.length() - 1, s.length())).equals("×")) {
            r = s.substring(0, s.length() - 1);
        } else {
            r = s.toString();
        }
        return r;
    }
}
