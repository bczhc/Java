package com.zhc.u;

import java.util.Scanner;

/**
 * 第二次编写的分解质因数 by zhc
 */
public class Factor1 {
    /**
     * 分解质因数优化算法，但依然不是最优。。
     * 因为计录分解耗时，并且System.out.println所用时间对最终耗时有影响，所以此类中todo那的单步输出被注释掉。
     * 如果查看分解步骤输出，可以把todo那的System.out.println取消注释。
     * by zhc 2018.11.6 22:05
     *
     * @param args args
     */
    public static void main(String[] args) {
        String r_;
        Factor1 l = new Factor1();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数字：");
        long n = sc.nextLong();
        long startTime = System.currentTimeMillis();
        r_ = l.resolve(n);
        long endTime = System.currentTimeMillis();
        System.out.println("最终结果：" + r_);
        System.out.println("用时: " + (endTime - startTime) + "ms");
        System.out.println();
        System.out.println();
        main(args);
    }

    /**
     * 分解质因数主函数
     *
     * @param n (long) 待分解的数
     * @return (String) 结果加耗时
     */
    private String resolve(long n) {
        //计时
        String r;
        StringBuilder s = new StringBuilder();
        s.append(n).append("=");
        if (n > 2) {
            while ((int) n != 1) {
                for (int i = 2; i <= n; i++) {
                    if (n % i == 0) {
                        s.append(i).append("×");
                         System.out.println("n:" + n + "    i:" + i); // todo
                        n /= i;
                        break;
                    }
                }
            }
        } else if (n < 1) {
            System.exit(0);
        } else if (n == 1) {
            s.append(1);
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