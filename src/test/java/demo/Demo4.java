package demo;

import org.junit.Test;
import pers.zhc.u.CanDoHandler;
import pers.zhc.u.Latch;
import pers.zhc.u.util.ClockHandler;
import pers.zhc.u.util.HandlerCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo4 {
    public Demo4() {
        System.out.println("new");
    }

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
        HandlerCallback<Integer> callback = a -> System.out.println("a = " + a);
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

    @Test
    public void test4() {
        CanDoHandler<Integer> handler = new CanDoHandler<>(System.out::println);
        handler.start();
        new Thread(() -> handler.push(1)).start();
        try {
            new CountDownLatch(1).await();
            handler.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        Latch latch = new Latch();
        latch.suspend();
        new Thread(() -> {
            System.out.println(1);
            latch.await();
            System.out.println(2);
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.stop();
        }).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService es = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            int finalI = i;
            es.execute(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("i = " + finalI);
            });
        }
        es.shutdown();
        Thread.sleep(1000);
        latch.countDown();
        Thread.sleep(1000);
    }

    @Test
    public void test7() throws InterruptedException {
        T7 t7 = new T7();
        new Thread(() -> {
            System.out.println("a");
            t7.await();
            System.out.println("b");
        }).start();
        Thread.sleep(1000);
        new Thread(() -> System.out.println(t7.f2())).start();
        Thread.sleep(3000);
    }

    @Test
    public void test8() throws InterruptedException {
        f8();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notifyAll();
        }).start();
    }

    private synchronized void f8() throws InterruptedException {
        this.wait();
        System.out.println("1");
    }

    @Test
    public void test9() {
        I8 c = new I8() {
            @Override
            public void f(int i) {
                System.out.println("i = " + i);
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> c.f(finalI)).start();
        }
    }

    @Test
    public void test10() {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            threads.add(new Thread(() -> new C10().test()));
            threads.get(i).start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test11() throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(1);
        final int[] num = new int[]{0};
        Runnable r1 = () -> {
            synchronized (num) {
                try {
                    while (num[0] <= 30) {
                        if (num[0] % 2 == 0) {
                            System.out.println("t1: " + num[0]);
                            if (num[0] == 30) {
                                latch.countDown();
                                return;
                            }
                            ++num[0];
                        }
                        num.notify();
                        num.wait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable r2 = () -> {
            synchronized (num) {
                try {
                    while (num[0] < 30) {
                        if (num[0] % 2 == 1) {
                            System.out.println("t2: " + num[0]);
                            ++num[0];
                        }
                        num.notify();
                        if (num[0] != 30)
                            num.wait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        es.execute(r1);
        es.execute(r2);
        latch.await();
        es.shutdownNow();
    }

    @Test
    public void test12() throws InterruptedException {
        T7 t7 = new T7();
        Thread thread = new Thread(() -> {
            System.out.println("1");
            t7.await();
            System.out.println("2");
        });
        thread.start();
        Thread.sleep(1000);
        t7.f2();
        thread.join();
    }

    private static class C12 {
        private final Object lock = new Object();
        private void await() throws InterruptedException {
            synchronized (lock) {
                lock.wait();
            }
        }

        private void go() {
            lock.notify();
        }
    }

    interface I8 {
        void f(int i);
    }

    private static class T7 {
        boolean stop = false;

        private void await() {
            for (; ; ) {
                if (stop) break;
            }
        }

        public int f2() {
            System.out.println("c");
            this.stop = true;
            return 0;
        }
    }

    private static class C10 {
        private static final Object lock = new Object();

        private void test() {
            synchronized (lock) {
                System.out.println("1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2");
            }
        }
    }

    @Test
    public void test13() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("ok");
    }
}
