package pers.zhc.u;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
     * 20190705改成2线程，其中一判断原数是否为质数，是就强制结束线程池，返回原数，否则等待另一线程分解结果
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

    public String factor(long number) {
        final boolean[] f = {true};
        final String[] r = new String[1];
        long[] n = new long[1];
        n[0] = number;
        StringBuilder s = new StringBuilder();
        s.append(number).append("=");
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService es = Executors.newFixedThreadPool(2);
        if (number > 2) {
            es.execute(() -> {
                if (isPrime.isPrime_old(number)) {
                    s.append(number);
                    latch.countDown();
                }
            });
            es.execute(() -> {
                while ((int) n[0] != 1) {
                    for (int i = 2; i <= n[0]; i++) {
                        if (n[0] % i == 0) {
                            s.append(f[0] ? i : "×" + i);
                            f[0] = false;
                            n[0] /= i;
                            break;
                        }
                    }
                }
                latch.countDown();
            });
        } else if (n[0] == 1) {
            s.append(1);
            latch.countDown();
        } else if (n[0] < 1) {
            System.exit(0);
        } else {
            s.append(2);
            latch.countDown();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        es.shutdownNow();
        if ((s.substring(s.length() - 1, s.length())).equals("×")) {
            r[0] = s.substring(0, s.length() - 1);
        } else {
            r[0] = s.toString();
        }
        return r[0];
    }
}
