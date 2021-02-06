package pers.zhc.u;

public class LongWaitCountDownLatch {
    private int count;

    public LongWaitCountDownLatch(int count) {
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public synchronized void countDown() {
        if (--count == 0) {
            this.notify();
        }
    }

    public synchronized void increaseCount() {
        ++count;
    }

    public void await() {
        if (count == 0) return;
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                assert false;
            }
        }
    }

    public synchronized void interruptAndReset() {
        count = 0;
        this.notify();
    }
}
