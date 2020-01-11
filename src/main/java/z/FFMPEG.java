package z;

import pers.zhc.u.FileU;
import pers.zhc.u.common.Execute;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FFMPEG {
    private static FileU o = new FileU();
    private static FFMPEG tO = new FFMPEG();

    public static void main(String[] args) {
        String dir = "/media/zhc/HDD1/DownloadMusic";
        File d = new File(dir);
        List<String> m = new ArrayList<>();
        //noinspection ResultOfMethodCallIgnored
        d.list((de, name) -> {
            if (o.getFileExtension(name).equalsIgnoreCase("fLaC")) {
                m.add(dir + "/" + name);
            }
            return true;
        });
        int size = m.size();
        System.out.println("size = " + size);
        ExecutorService es = Executors.newFixedThreadPool(4);
        double k = 0;
        int t = size / 4;
        int b = size % 4;
        for (int i = 0; i < t; i++) {
            CountDownLatch latch = new CountDownLatch(4);
            String[] names = new String[4];
            for (int j = 0; j < 4; j++) {
                names[j] = m.get(4 * i + j);
            }
            for (String name : names) {
                es.execute(tO.getRunnable(name, latch));
            }
            System.out.println(Arrays.toString(names));
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ++k;
            System.out.println(k / ((double) t) * 100 + "-----------------------------------------------------------------" +
                    "-------------------------------------");
        }
        String[] lN = new String[b];
        for (int i = 0; i < b; i++) {
            lN[i] = m.get(4 * t + i);
        }
        CountDownLatch latch = new CountDownLatch(b);
        for (String name : lN) {
            new Thread(tO.getRunnable(name, latch)).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("lN = " + Arrays.toString(lN));
        System.out.println("ok");
    }

    private Runnable getRunnable(String name, CountDownLatch latch) {
        return () -> {
            String[] c = new String[]{"ffmpeg", "-i", null, null};
            c[2] = name;
            c[3] = "/media/zhc/6C8C-2CCE/o/" + o.changeFileExtension(new File(name).getName(), "mp3");
            try {
                Execute.exec(c, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            latch.countDown();
        };
    }

    public static class Move {
        public static void main(String[] args) {
            String dir = "/home/zhc/Downloads/m";
            File d = new File(dir);
            //noinspection ResultOfMethodCallIgnored
            d.list((dir1, name) -> {
                if (o.getFileExtension(name).equalsIgnoreCase("mp3")) {
                    File file = new File(name);
                    try {
                        FileU.FileCopy(file, new File("/media/zhc/6C8C-2CCE/o/" + file.getName()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            });
        }
    }
}