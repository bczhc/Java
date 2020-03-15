package pers.zhc.u;

public class Latch {
    private boolean stop = false;

    public void await() {
        for (; ; ) {
            if (stop) break;
        }
//        new CountDownLatch().await();
    }

    public void suspend() {
        new Thread(() -> {
            stop = false;
            System.out.println("stop false");
        }).start();
    }

    public void stop() {
        new Thread(() -> {
            stop = true;
            System.out.println("stop true");
        }).start();
    }
}
