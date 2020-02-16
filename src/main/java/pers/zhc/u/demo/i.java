package pers.zhc.u.demo;

import java.util.Scanner;

public class i {
    /*public static void pers.zhc.u.kotlin.main(String[] args) {
        i i = new i();
        System.out.println(i.jiechengsum(3));
    }

    private Long jiechengsum(int x) {
        int sum = 0, sum1 = 1;
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= i; j++) {
                sum1 *= j;
            }
            sum += sum1;
        }
        return (long) sum;
    }*/

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请依次输入三个数");
        long a = sc.nextLong();
        long b = sc.nextLong();
        long c = sc.nextLong();
        System.out.println("最大的数为" + ((((a <= b) ? b : a) <= c) ? c : (a <= b) ? b : a));
    }
}