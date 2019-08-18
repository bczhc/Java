package pers.zhc.u.demo.Thread;

import pers.zhc.u.FileU;
import pers.zhc.u.common.Arr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class b {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Math.random());
            }
        });
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        });
        es.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        });
        es.shutdown();
    }
}

class t0 implements Runnable {
    private Thread t;
    private static CountDownLatch latch = new CountDownLatch(1000);

    @Override
    public void run() {
        System.out.println(Math.random());
        latch.countDown();
    }

    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            new t0().S();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long l2 = System.currentTimeMillis();
        System.out.println("l2 - l1 = " + (l2 - l1));
    }

    private void S() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }
}

class s1 {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(1000);
        ExecutorService es = Executors.newCachedThreadPool();
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Math.random());
                    latch.countDown();
                }
            });
        }
        es.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long l2 = System.currentTimeMillis();
        System.out.println("(l2 - l1) = " + (l2 - l1));
    }
}

class s2 {
    public static void main(String[] args) {
        final String[][] md5 = new String[1][];
        final List<File> l = new ArrayList<>();
        new FileU.TraversalFile("F:\\DownloadMusic\\").Do(new FileU.TraversalFileDo() {
            @Override
            public void f(File f) {
                l.add(f);
            }

            @Override
            public void d(File d) {

            }

            @Override
            public void a(File a) {

            }
        });
        File[] files = l.toArray(new File[0]);
        final CountDownLatch[] latch = {null};
        //noinspection unchecked
        new Arr.SeparateArr<>(files, 10).separate(new Arr.SeparateArrDo() {
            @Override
            public void f(Object[] a) {
                ExecutorService es = Executors.newCachedThreadPool();
                int length = a.length;
                latch[0] = new CountDownLatch(length);
                md5[0] = new String[length];
                for (final Object o : a) {
                    es.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String md5S = FileU.getMD5String(new File(String.valueOf(o)));
                                System.out.println(md5S);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            latch[0].countDown();
                        }
                    });
                }
                try {
                    latch[0].await();
                    es.shutdown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

class s3 {
    public static void main(String[] args) {
        final ExecutorService es = Executors.newCachedThreadPool();
        new FileU.TraversalFile("F:\\DownloadMusic\\").Do(new FileU.TraversalFileDo() {
            @Override
            public void f(final File f) {
                es.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(FileU.getMD5String(f));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void d(File d) {

            }

            @Override
            public void a(File a) {

            }
        });
        es.shutdown();
    }
}