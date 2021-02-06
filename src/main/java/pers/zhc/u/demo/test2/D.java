package pers.zhc.u.demo.test2;

import pers.zhc.u.LongWaitCountDownLatch;

public class D {
    public static void main(String[] args) {
        LongWaitCountDownLatch latch = new LongWaitCountDownLatch(1);
        new Thread(() -> {
            latch.await();
            System.out.println("1");
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }).start();
    }
}
