package com.zhc.u;

import java.util.Arrays;

/**
 * 排序 by zhc
 * 2018.9.22 11:01
 */
public class Order {
    private static int[] a = {};
    private static int[] arr_new = new int[a.length];
    private static int min_n, arr_new_i = -1;

    public static void main(String[] args) {
        System.out.println("原 " + Arrays.toString(a));
        System.out.println(order(a, "01"));
    }

    private static int getMin_n(int[] arr) {
        return blMin(arr, arr_getMin(arr));
    }

    private static int arr_getMin(int[] arr) {
        int min_ = 0;
        if (arr.length > 1) {
            min_ = Math.min(arr[0], arr[1]);
            for (int i = 2; i < arr.length; i++) {
                min_ = Math.min(min_, arr[i]);
            }
        } else if (arr.length == 1) {
            min_ = arr[0];
        }
        return min_;
    }

    private static int blMin(int[] arr, int min_) {
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] == min_) {
                min_n = j;
            }
        }
        return min_n;
    }

    private static int[] left_1_main(int[] arr, int n) {

        int[] arr_tmp = new int[arr.length - 1];
        for (int k = 0; k < arr.length; k++) {
            if (k < n) {
                arr_tmp[k] = arr[k];
            } else if (k > n) {
                arr_tmp[k - 1] = arr[k];
            }
        }
        arr_new_i++;
        arr_new[arr_new_i] = arr[n];
        if (arr_tmp.length != 0) {
            left_1_main(arr_tmp, getMin_n(arr_tmp));
        }
        return arr_new;
    }

    /**
     * 排序主函数
     *
     * @param x int[] 原来的数组
     * @param o String 从小到大排序"01"，反之"10"
     * @return int[] 排序完成的数组
     */
    public static String order(int[] x, String o) {
        if (x.length == 0) {
            System.out.println((String) null);
            System.exit(0);
        }
        int m = 0;
        String result = null;
        int[] result_01 = left_1_main_(x, getMin_n(x)), result_10;
        if (o.equals("01")) {
            result = Arrays.toString(result_01);
        } else if (o.equals("10")) {
            result_10 = new int[result_01.length];
            for (int l = result_01.length - 1; l >= 0; l--) {
                result_10[l] = result_01[m];
                m += 1;
            }
            result = Arrays.toString(result_10);
        }
        return result;
    }

    private static int[] left_1_main_(int[] arr, int n) {
        arr_new_i = -1;
        return left_1_main(arr, n);
    }

}