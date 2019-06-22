package com.zhc.u;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Pi {
    private static Power p_o = new Power();

    public static void main(String[] args) {
        System.out.println("new Pi().acot(1, 1000, 1000) = " + new Pi().acot(1, 10, 11));
    }

    public static String pi(int length) {
        /*String result;
        int limit = 1000000;
        String numberNot0 = "[1-9]";
        if (length < 2) length = 2;

        return result;*/
        return "";
    }


    public static String pi2(int length) {
        return "";
    }

    private double atan(int n, int p) {
        double r = n;
        boolean b = false;
        for (int i = 3; i <= p; i += 2) {
            if (b) {
                r += Math.pow(r, i) / i;
                b = false;
            } else {
                r -= Math.pow(r, i) / i;
                b = true;
            }
        }
        return r;
    }

    private BigDecimal acot(int n, int precision, long t) {
        RoundingMode rM = RoundingMode.FLOOR;
        BigDecimal bd = BigDecimal.valueOf(n);
        BigDecimal r = BigDecimal.ONE.divide(bd, precision, rM);
        boolean b = true;
        for (long i = 3L; i <= t; i += 2L) {
            BigDecimal bd_i = BigDecimal.valueOf(i);
            if (b) {
                r = r.subtract(
                        BigDecimal.ONE.divide(
                                bd_i.multiply(p_o.power(bd, bd_i))
                                , precision, rM
                        )
                );
                b = false;
            } else {
                r = r.add(
                        BigDecimal.ONE.divide(
                                bd_i.multiply(p_o.power(bd, bd_i))
                                , precision, rM
                        )
                );
                b = true;
            }
        }
        return r;
    }
}