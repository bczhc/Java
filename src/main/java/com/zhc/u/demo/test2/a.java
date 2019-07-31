/*
package com.zhc.u.com.zhc.u.com.zhc.u.com.zhc.u.demo.test2;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class A {

    public static void main(String[] args) {
        A A = new A();
        BigDecimal sum = BigDecimal.valueOf(0);
        for (int i = 1; i < 1000000; i++) {
            BigDecimal ii = BigDecimal.valueOf(i);
            sum = sum.add(BigDecimal.valueOf(1).divide(ii.multiply(ii), 500, RoundingMode.FLOOR));
        }
        System.out.println(A.bdSQRT(new BigDecimal(6).multiply(sum)).toString());
    }

    private BigDecimal bdSQRT(BigDecimal bd) {
        BigDecimal x = null;
        // TODO Auto-generated method stub
        BigDecimal _2 = BigDecimal.valueOf(2.0);
        int precision = 100;//精度
        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);            //某个操作使用的数字个数；结果舍入到此精度
        if (bd.compareTo(BigDecimal.ZERO) == 0) System.out.println(0);
        else {
            x = bd;
            int cnt = 0;
            while (cnt < precision) {
                x = (x.add(bd.divide(x, mc))).divide(_2, mc);
                cnt++;
            }
        }
        return x;
    }
}



import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class A {

    BigDecimal bdSQRT(String[] args) {
        // TODO Auto-generated method stub
        Scanner in = new Scanner(System.in);
        BigDecimal A = in.nextBigDecimal();
        BigDecimal _2 = BigDecimal.valueOf(2.0);
        int precision = 100;//精度
        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);            //某个操作使用的数字个数；结果舍入到此精度
        if (A.compareTo(BigDecimal.ZERO) == 0) System.out.println(0);
        else {
            BigDecimal x = A;
            int cnt = 0;
            while (cnt < precision) {
                x = (x.add(A.divide(x, mc))).divide(_2, mc);
                cnt++;
            }
        }
        return x;
    }
}


//默认输出小数点后30位

import java.math.BigDecimal;
import java.math.RoundingMode;

public class A {

    public static void main(String[] args) {
        final int PRECISION = 100;//计算精度
        final int THENUMBEROFCIRCLES = 1;//循环次数
        BigDecimal PI = new BigDecimal(0);
        System.out.println("正在计算中...请稍后...");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < THENUMBEROFCIRCLES; i++) {
            //PI=PI+1/16^i(4/(8i+1)-2/(8i+4)-1/(8n+5)-1/(8n+6))
            PI = PI.add((BigDecimal.valueOf(1).divide(BigDecimal.valueOf(16).pow(i), 100, RoundingMode.FLOOR)).multiply((BigDecimal.valueOf(4)
                    .divide(BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(i)).add(BigDecimal.valueOf(1)), PRECISION, BigDecimal.ROUND_DOWN))//ROUND_DOWN接近零的舍入模式（截取）
                    .subtract(BigDecimal.valueOf(2).divide(BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(i)).add(BigDecimal.valueOf(4)), PRECISION, BigDecimal.ROUND_DOWN))
                    .subtract((BigDecimal.valueOf(1).divide(BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(i)).add(BigDecimal.valueOf(5)), PRECISION, BigDecimal.ROUND_DOWN)))
                    .subtract((BigDecimal.valueOf(1).divide(BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(i)).add(BigDecimal.valueOf(6)), PRECISION, BigDecimal.ROUND_DOWN)))));
        }
        System.out.println("PI=" + PI.setScale(100, BigDecimal.ROUND_DOWN) + "\n共用时："//输出精度设置成30
                + (System.currentTimeMillis() - startTime) / 1000.0 + "秒");
    }
}
*/


package com.zhc.u.demo.test2;

import com.zhc.u.demo.r;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class a {
    public static void main(String[] args) {
        r demo_r = new r();
        long times = 100; //次数
        int DividePrecision = 100; //除法运算精度
        BigDecimal sum_r = BigDecimal.ZERO;
        BigDecimal _8n, a, b, c, d, m, _1s16pow2;
        for (int n = 0; n < times; n++) {
            _8n = BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(n));
            _1s16pow2 = BigDecimal.ONE.divide(demo_r
                    .power(BigDecimal.valueOf(16), BigDecimal.valueOf(n)), DividePrecision, RoundingMode.FLOOR);
            a = BigDecimal.valueOf(4).divide(_8n.add(BigDecimal.ONE), DividePrecision, RoundingMode.FLOOR);
            b = BigDecimal.valueOf(2).divide(_8n.add(BigDecimal.valueOf(4)), DividePrecision, RoundingMode.FLOOR);
            c = BigDecimal.valueOf(1).divide(_8n.add(BigDecimal.valueOf(5)), DividePrecision, RoundingMode.FLOOR);
            d = BigDecimal.valueOf(1).divide(_8n.add(BigDecimal.valueOf(6)), DividePrecision, RoundingMode.FLOOR);
            m = a.subtract(a).subtract(b).subtract(c).subtract(d);
            sum_r = sum_r.add(_1s16pow2.multiply(m));
        }
        System.out.println(sum_r.toString());
    }
}