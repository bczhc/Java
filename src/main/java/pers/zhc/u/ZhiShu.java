package pers.zhc.u;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * 质数生成器 by zhc
 * 2018.9.24 21:08
 */
public class ZhiShu {
    private static Scanner sc = new Scanner(System.in);
    private static String CF = "";

    public static void main(String[] args) {
        System.out.println("请输入范围，第一个输入头数，回车后输入尾数，回车。<不能小于0，最高19位>：");
        long a = sc.nextLong(), b = sc.nextLong();
        if (a < 0 || b < 0) System.exit(0);
        long time_start = System.currentTimeMillis();
        System.out.println(ZS(a, b));
        long time_end = System.currentTimeMillis();
        long time = time_end - time_start;
        System.out.println("生成共用" + time + "ms");
        if (!CF.equals("yes")) {
            System.out.println("是否重复？<\"yes\" or any key other than \"yes\">");
            CF = sc.next();
        }
        if (CF.equals("yes")) {
            main(args);
        }
    }

    private static List<Integer> ZS(long a, long b) {
        int k = 0;
        List<Integer> list = new ArrayList<>();
        for (long i = a; i <= b; i++) {
            if (isZS(i)) {
                list.add(0);
                list.set(k, (int) i);
                ++k;
            }
        }
        return list;
    }

    private static boolean isZS(long n) {
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