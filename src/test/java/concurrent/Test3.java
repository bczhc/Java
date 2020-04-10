package concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * @author bczhc
 */
public class Test3 {
    static Thread t1 = null, t2 = null, t3 = null;
    public static void main(String[] args) {
        final ParkPrinter pp = new ParkPrinter(5);
        t1 = new Thread(() -> pp.print(1, t2));
        t2 = new Thread(() -> pp.print(2, t3));
        t3 = new Thread(() -> pp.print(3, t1));
        t1.start();
        t2.start();
        t3.start();
        LockSupport.unpark(t1);
    }
}

class ParkPrinter {
    private int loopNum;

    public ParkPrinter(int loopNum) {
        this.loopNum = loopNum;
    }

    void print(int num, Thread nextThread) {
        for (int i = 0; i < loopNum; i++) {
            LockSupport.park();
            System.out.println(num);
            LockSupport.unpark(nextThread);
        }
    }
}
