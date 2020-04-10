package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bczhc
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        AwaitSignal as = new AwaitSignal(5);
        Condition c1 = as.newCondition();
        Condition c2 = as.newCondition();
        Condition c3 = as.newCondition();
        new Thread(() -> as.print(1, c1, c2)).start();
        new Thread(() -> as.print(2, c2, c3)).start();
        new Thread(() -> as.print(3, c3, c1)).start();
        Thread.sleep(1000);
        as.lock();
        try {
            c1.signal();
        } finally {
            as.unlock();
        }
    }
}

class AwaitSignal extends ReentrantLock {
    private int loopNum;

    public AwaitSignal(int loopNum) {
        this.loopNum = loopNum;
    }

    void print(int num, Condition current, Condition next) {
        for (int i = 0; i < loopNum; i++) {
            lock();
            try {
                current.await();
                System.out.println(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                next.signal();
                unlock();
            }
        }
    }
}
