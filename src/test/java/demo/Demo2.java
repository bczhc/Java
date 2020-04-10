package demo;

import org.junit.Test;
import pers.zhc.u.Random;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo2 {

    @Test
    public void test() {
        List<Integer> list = new SynchronisedList<>();
        ExecutorService es = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            es.execute(() -> {
                for (int j = 0; j < 1000; j++) {
                    list.add(Random.ran_sc(0, 100));
                }
                latch.countDown();
            });
        }
        es.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list);
    }

    private static class SynchronisedList<T> extends LinkedList<T> {
        @Override
        public boolean add(T t) {
            synchronized (this) {
                return super.add(t);
            }
        }

        @Override
        public T removeLast() {
            synchronized (this) {
                return super.removeLast();
            }
        }
    }
}
