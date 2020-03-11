package demo;

import org.junit.Test;
import pers.zhc.u.util.ClockHandler;
import pers.zhc.u.util.ClockHandlerCallback;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo4 {
    @Test
    public void test() throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(1);
        CountDownLatch latch1 = new CountDownLatch(1);
        es.execute(() -> {
            System.out.println(1);
            latch1.countDown();
            for (int i = 0; i < 1000; i++) {
                System.out.println(2);
            }
            latch.getCount();
        });
        Thread thread = new Thread(() -> {
            try {
                latch1.await();
                System.out.println("shutdownNow");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            es.shutdownNow();
            latch.countDown();
        });
        thread.start();
        thread.join();
        latch.await();
        System.out.println("done.");
    }

    @Test
    public void test3() {
        ClockHandlerCallback<Integer> callback = a -> System.out.println("a = " + a);
        ClockHandler<Integer> clockHandler = new ClockHandler<>(callback, 1000);
        ClockHandler.ParamReference<Integer> paramReference = clockHandler.getParamReference();
        clockHandler.start();
        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                paramReference.param = i;
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            clockHandler.stop();
        }).start();
        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws InterruptedException {
        Runnable r = () -> System.out.println("r");
        ExecutorService es = Executors.newFixedThreadPool(1);
        boolean[] b = new boolean[]{true};
        es.execute(() -> {
            while (b[0]) {
                r.run();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            b[0] = false;
            es.shutdown();
        }).start();
        new CountDownLatch(1).await();
    }
}
