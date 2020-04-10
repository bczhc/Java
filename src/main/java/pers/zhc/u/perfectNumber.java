package pers.zhc.u;

import java.util.ArrayList;
import java.util.List;

public class perfectNumber {
    public static void main(String[] args) {
        perfectNumber Main = new perfectNumber();
        for (long i = 2; i <= 1000000000000000000L; i += 2) {
            long sum = 0L;
            Object[] r = Main.fact(i);
            for (long j = 0; j < r.length; j++) {
                sum += (Long) r[(int) j];
            }
            if (sum == i) System.out.println(i);
        }
        System.out.println("OK!");
    }

    private Object[] fact(long n) {
        List<Long> l = new ArrayList<>();
        for (long i = 1; i < n; i++) {
            if (n % i == 0) l.add(i);
        }
        return l.toArray();
    }
}