package concurrent;

/**
 * @author bczhc
 */
public class Test1 {
    public static void main(String[] args) {
        Printer printer = new Printer(1, 5);
        new Thread(() -> printer.print(1, 2)).start();
        new Thread(() -> printer.print(2, 3)).start();
        new Thread(() -> printer.print(3, 1)).start();
    }
}

class Printer {
    private int loopNum;
    private int nextNum;

    public Printer(int initNum, int loopNum) {
        this.nextNum = initNum;
        this.loopNum = loopNum;
    }

    void print(int printNum, int nextNum) {
        for (int i = 0; i < loopNum; i++) {
            synchronized (this) {
                while (this.nextNum != printNum) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(printNum);
                this.nextNum = nextNum;
                this.notifyAll();
            }
        }
    }
}
