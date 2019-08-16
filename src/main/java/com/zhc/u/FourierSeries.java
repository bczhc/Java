package com.zhc.u;


import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("WeakerAccess")
public class FourierSeries {
    private double T;
    public Definite definite;
    private double omega;

    public FourierSeries(double T) {
        this.T = T;
        omega = (Math.PI * 2) / this.T;
        definite = new Definite();
    }

    public double f_f(double x) {
        return 0;
    }

    public static void main(String[] args) {
        FourierSeries fs = new FourierSeries(30D) {
            @Override
            public double f_f(double x) {
                if (x < 10) return x;
                if (x >= 10 && x < 20) return -x + 20;
                if (x >= 20 && x < 25) return x - 20;
                return Math.sin(Math.pow(x, Math.cos(x)));
            }
        };
        fs.definite.n = 10000;
        fs.m();
    }

    public double a0() {
        return (2 / this.T) * definite.getR(0, this.T, this::f_f);
    }

    public double F(int nNum, double x) {
        CountDownLatch latch = new CountDownLatch(nNum - 1);
        double a0 = a0();
        double[] r = new double[nNum - 1];
        double sum = 0;
        for (int i = 1; i < nNum; i++) {
            int finalI = i;
            new Thread(() -> {
                r[finalI - 1] = fu(finalI, x);
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (double v : r) {
            sum += v;
        }
        return a0 / 2 + sum;
    }

    public void m() {
        int tN = 16;
        ExecutorService es = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(tN);
        double[] r = new double[tN];
        int j = 0;
        for (double i = 0; i < 20; i += .1) {
            if (i == 20 - .1) return;
            double finalI = i;
            int finalJ = j;
            CountDownLatch finalLatch = latch;
            es.execute(() -> {
                System.out.println("x: " + finalI + "\t" + (r[finalJ % tN] = F(30, finalI)));
                finalLatch.countDown();
            });
            if ((j + 1) % tN == 0 && j != 0) {
                try {
                    latch.await();
                    latch = new CountDownLatch(tN);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("r = " + Arrays.toString(r));
            }
            ++j;
        }
    }

    public double fu(int n, double x) {
        return aN(n) * Math.cos(((double) n) * this.omega * x) + bN(n) * Math.sin(((double) n) * this.omega * x);
    }

    public double aN(int n) {
        return (2 / this.T) * definite.getDefiniteIntegralByTrapezium(0, this.T, x -> f_f(x) * Math.cos(((double) n) * this.omega * x));
    }

    public double bN(int n) {
        return (2 / this.T) * definite.getDefiniteIntegralByTrapezium(0, this.T, x -> f_f(x) * Math.sin(((double) n) * this.omega * x));
    }
}