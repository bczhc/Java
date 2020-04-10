package demo;

public class SyncDemo implements Runnable {
    private final Object prev;
    private final Object cur;
    private String name;

    public SyncDemo(String name, Object prev, Object cur) {
        this.name = name;
        this.prev = prev;
        this.cur = cur;
    }

    public static void main(String[] args) {
        int num = 5;

        Object[] locks = new Object[num];
        String[] names = {"A", "B", "C", "D", "E"};
        SyncDemo[] runnable = new SyncDemo[num];

        for (int i = 0; i < num; i++) {
            locks[i] = new Object();
        }

        for (int i = 0; i < num; i++) {
            runnable[i] = new SyncDemo(names[i], locks[(i - 1 + num) % num] , locks[i]);
        }

        for (int i = 0; i < num; i++) {
            new Thread(runnable[i]).start();
            try {
                Thread.sleep(100);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        int count = 100;
        while (count > 0) {
            synchronized (prev) {
                synchronized (cur) {
                    System.out.println(name);
                    count--;
                    cur.notify();
                }
                try {
                    prev.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}