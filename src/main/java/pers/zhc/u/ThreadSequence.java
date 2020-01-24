package pers.zhc.u;

import pers.zhc.u.common.Documents;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSequence {
    private final int threadNum;
    private List<ThreadSequenceRunnableInterface> runnableList;
    private ExecutorService es;

    public ThreadSequence(int threadNum) {
        es = Executors.newFixedThreadPool(threadNum);
        this.threadNum = threadNum;
        runnableList = new ArrayList<>();
    }

    public static void main(String[] args) {
        ThreadSequence threadSequence = new ThreadSequence(1);
        for (int i = 0; i < 1; i++) {
            threadSequence.execute((j, t) -> {
                System.out.println(j + "\t" + t);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadSequence.start(() -> System.out.println("ok!"));
        System.out.println("ee");
    }

    public ExecutorService getExecutorService() {
        return this.es;
    }

    public void execute(ThreadSequenceRunnableInterface runnable) {
        runnableList.add(runnable);
    }

    public void start(@Documents.Nullable Runnable doneAction) {
        int t = -1;
        int size = runnableList.size();
        CountDownLatch latch = new CountDownLatch(0);
        for (int i = 0; i < size; i++) {
            if (i % threadNum == 0) {
                try {
                    latch.await();
                    ++t;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch = new CountDownLatch(threadNum);
            }
            int finalI = i;
            CountDownLatch finalLatch = latch;
            int finalT = t;
            es.execute(() -> {
                runnableList.get(finalI).m(finalI, finalT);
                finalLatch.countDown();
                if (finalI == size - 1) {
                    if (doneAction != null) {
                        doneAction.run();
                    }
                }
            });
        }
        es.shutdown();
    }

    public void shutdownNow() {
        this.es.shutdownNow();
    }

    public interface ThreadSequenceRunnableInterface {
        /**
         * 线程序列执行接口
         *
         * @param i 当前线程为第i个提交的
         * @param t 当前线程是第t轮执行（从0开始）
         */
        void m(int i, int t);
    }
}