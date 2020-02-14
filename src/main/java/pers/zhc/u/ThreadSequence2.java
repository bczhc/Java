package pers.zhc.u;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSequence2 {
    private List<Runnable> tasks;
    //    private Running running;
    private final int threadNum;
    private Runnable[] running;
    private ExecutorService es;

    public ThreadSequence2(int threadNum) {
        this.threadNum = threadNum;
        tasks = new ArrayList<>();
        running = new Runnable[threadNum];
//        running = new Running()
        es = Executors.newCachedThreadPool();
    }

    public void addTask(Runnable runnable) {
        synchronized (this) {
            tasks.add(runnable);
        }
    }

    public void start() {

    }

    /*private static class Running {
        private List<Runnable> running;

        private Running() {
            running = new ArrayList<>();
        }

        private synchronized void add(Runnable r) {
            this.running.add(r);
        }

        private synchronized void remove(Runnable r) {
            this.running.remove(r);
        }
    }*/
    public static void main(String[] args) {

    }
}