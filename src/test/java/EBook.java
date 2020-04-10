import pers.zhc.u.FileU;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EBook {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(12);
        CountDownLatch latch = new CountDownLatch(113);
        File dir = new File("./fh");
        System.out.println("dir.mkdirs() = " + dir.mkdirs());
        for (int i = 1; i <= 113; i++) {
            int finalI = i;
            es.submit(() -> {
                File f = new File(dir, finalI + ".jpg");
                try {
                    URL url = new URL(String.format("http://www.fhebook.cn/h5book/202002/yuwen/bixiu4b/files/mobile/%d.jpg?x-oss-process=image/format,webp", finalI));
                    FileU.DownloadWeb(url, f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                System.out.println(finalI);
            });
        }
        es.shutdown();
        try {
            latch.await();
            System.out.println("done.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
