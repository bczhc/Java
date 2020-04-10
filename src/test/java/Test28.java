import org.junit.jupiter.api.Test;

public class Test28 {
    @Test
    void test1() {
        new Thread(this::f, "t1").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            this.notifyAll();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void f() {
        System.out.println(1);
        synchronized (this) {
            try {
                System.out.println("wait");
                this.wait();
                System.out.println("awake");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(3);
        }
        System.out.println(2);
    }
}
