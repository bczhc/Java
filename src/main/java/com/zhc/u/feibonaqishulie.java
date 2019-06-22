package com.zhc.u;/*
 *输入一个斐波那契数成变量num，运行会解出这是斐波那契数列中的第几个。
 * 输入一个数成变量w，运行会计算斐波那契数列中的第w个数是多少。
 * By zhc
 * 2018.8.15 14:10
 */

import java.util.Scanner;

public class feibonaqishulie {

    private static int a = 1, b = 1, c, r, k = 1, cho = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你想求解的斐波那契数列中的一个数");
        long num = sc.nextLong();
        System.out.println("请输入你想计算的第n个斐波那契数");
        long w = sc.nextLong();
        //solve
        cho = 1;
        do {
            rnum(k);
            if (r == num) {
                System.out.print(num + "是斐波那契数列中的第" + k + "个数");
                if (k == 1) {
                    System.out.println("或第二个数");
                }
                break;
            } else if (r > num) {
                System.out.println("在斐波那契数列中没有找到" + num);
                break;
            }
            k++;
        } while (r != -1);
        ////////////////////////////////////
        System.out.println();
        ////////////////////////////////////
        k = 0;
        r = 0;
        a = 1;
        b = 1;
        c = 0;
        cho = 0;
        rnum(w);
        System.out.println("斐波那契数列中第" + w + "个数是" + r);
    }

    private static void foo(long k) {
        for (int i = 1; i <= (k - 2); i++) {
            if (cho == 1) {
                k = 0; //清理工作
            }
            r = a + b;
            c = r;
            a = b;
            b = c;
        }
    }

    private static void rnum(long k) {
        if (k > 0) {
            if (k <= 2) {
                r = 1;//固定
            }
            //递归型求斐波那契数列第k个
            else {
                foo(k);
            }
        }
    }
}