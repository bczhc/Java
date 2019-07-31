import com.alibaba.fastjson.JSON;
import com.zhc.u.IsPrime;
import org.intellij.lang.annotations.Language;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class T1 {
    public static void main(String[] args) throws InterruptedException {
        final Boolean[] b = {null};
        ExecutorService es = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(1);
        es.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (latch.getCount() == 1) latch.countDown();
            b[0] = true;
        });
        es.execute(() -> {
            try {
                Thread.sleep(2000);
                b[0] = false;
            } catch (InterruptedException ignored) {
            }
            if (latch.getCount() == 1) latch.countDown();
        });
        latch.await();
        es.shutdownNow();
        System.out.println("b = " + b[0]);
    }
}


class T2 {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            latch.countDown();
            latch.countDown();
            latch.countDown();
            latch.countDown();
            latch.countDown();
            latch.countDown();
        }).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("over");
    }
}

class T3 {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(() -> {
            System.out.println("1");
            es.shutdownNow();
            System.out.println("2");
        });
        es.execute(() -> {
            System.out.println("3");
            System.out.println("4");
            es.shutdownNow();
            System.out.println("5");
        });
        System.out.println("6");
    }
}

class T4 {
    public static void main(String[] args) {
        IsPrime isPrime = new IsPrime();
        for (long i = 100000000000L; i < 100000000000000000L; i++) {
            if (isPrime.isPrime(i)) {
                System.out.println(i);
            }
        }
    }
}

class T5 {
    public static void main(String[] args) {
        @Language("JSON") String json = "{\n" +
                "  \"a\": \"b\",\n" +
                "  \"b\": \"c\",\n" +
                "  \"c\": 1,\n" +
                "  \"d\": {\n" +
                "    \"a\": true,\n" +
                "    \"b\": 123\n" +
                "  }\n" +
                "}";
        System.out.println("JSON.parse(\"\") = " + JSON.parse("{a: \"b\", \"c\" : \"d\"}"));
    }
}