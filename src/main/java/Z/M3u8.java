package Z;

import com.zhc.u.FileU;
import com.zhc.u.common.Arr;
import com.zhc.u.common.ReadIS;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class M3u8 {
    public static void main(String[] args) {
        /*URL url = new URL("http://m.nmgk.com/v/538-1-2.html");
        URLConnection urlConnection = url.openConnection();
        InputStream is = urlConnection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        while (s != null) {
            if (s.matches(" *<iframe src=.*\\.m3u8\".*"))
                break;
            s = br.readLine();
        }
        br.close();
        isr.close();
        is.close();
//        System.out.println(s);
        String m3u8Url = null;
        Matcher m = Pattern.compile("url=.*\\.m3u8").matcher(Objects.requireNonNull(s));
        while (m.find()) {
            String[] split = m.group(0).split("url=");
            m3u8Url = split[split.length - 1];
        }
        if (m3u8Url != null) {
            solve(m3u8Url);
        }*/
        /*solve("https://web.etiantian.com/ett20/hls/hd.m3u8?p=gzhx000042&s=a&t=1563273333&v=5cbc4a7744be4ede31b41bba6424d80a&h=http%3A%2F%2Fhd.etiantian.com"
                , 30);*/
        System.out.print("m3u8URL: ");
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.print("thread num: ");
        int i = sc.nextInt();
        solve(s, i);
    }

    @SuppressWarnings("WeakerAccess")
    public static void solve(String m3u8Url, int threadNum) {
        try {
            URL url = new URL(m3u8Url);
            InputStream is = url.openStream();
            List<String> c = new ArrayList<>();
            new ReadIS(is, StandardCharsets.UTF_8).read(line -> {
                if (!line.matches("#.*")) c.add(line);
            });
            List<String> urlList = new ArrayList<>();
            for (String s : c) {
                if (s.matches(".*\\.m3u8")) {
                    int lastIndexOf = m3u8Url.lastIndexOf('/');
                    String substring = m3u8Url.substring(0, lastIndexOf + 1);
//                    m3u8Url = substring + s;
                    m3u8Url = s.matches(".*http.*://.*") ? s : substring + s;
                    solve(m3u8Url, threadNum);
                } else {
                    File d = new File("./tsDownload");
                    if (!d.exists()) {
                        System.out.println("d.mkdir() = " + d.mkdir());
                    }
                    if (s.matches(".*\\.ts.*")) {
                        int lastIndexOf = m3u8Url.lastIndexOf('/');
                        String substring = m3u8Url.substring(0, lastIndexOf + 1);
                        String tsUrl = s.matches(".*http.*://.*") ? s : substring + s;
                        urlList.add(tsUrl);
                    }
                }
            }
            Object[] objects = urlList.toArray(new Object[0]);
            final CountDownLatch[] latch = {new CountDownLatch(threadNum)};
            final int[] count = {0};
            new Arr.SeparateArr<>(objects, threadNum).separate(a -> {
                ExecutorService es = Executors.newFixedThreadPool(threadNum);
                try {
                    for (Object o : a) {
                        es.execute(() -> {
                            try {
                                String tsUrl = o.toString();
                                int lastIndexOf = tsUrl.lastIndexOf('/');
                                File file = new File("./tsDownload/" + tsUrl.substring(lastIndexOf + 1));
                                if (!file.exists()) {
                                    OutputStream os = new FileOutputStream(file);
                                    FileU.DownloadWeb(tsUrl, os);
                                }
                                System.out.println(count[0] + "/" + objects.length);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            latch[0].countDown();
                            ++count[0];
                        });
                    }
                    latch[0].await();
                    es.shutdown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch[0] = new CountDownLatch(objects.length - count[0] < 30 ? objects.length - count[0] : threadNum);
            });
            System.out.println("开始合并…");
            File file1 = new File("./合并.mp4");
            OutputStream os = new FileOutputStream(file1);
            for (String s : urlList) {
                int lastIndexOf = s.lastIndexOf('/');
                File file = new File("./tsDownload/" + s.substring(lastIndexOf + 1));
                InputStream fis = new FileInputStream(file);
                byte[] buf = new byte[1024];
                int readLen;
                while (true) {
                    if ((readLen = fis.read(buf)) != -1) {
                        os.write(buf, 0, readLen);
                        os.flush();
                    } else break;
                }
                fis.close();
            }
            os.close();
            System.out.println("完成。");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Etiantian {
        public static void main(String[] args) throws IOException {
//            Scanner sc = new Scanner(System.in);
//            String next = sc.next();
            String next = "http://web.etiantian.com/ett20/study/studydir/go2task.jsp?listID=484021367&dirId=13660&taskID=13660&safeCode=733765ce3bbdd7f320917ccda1cc18d4&taskId=3&resourceId=382358&s=2a986040534839726ca01d52fe61f18b";
            URL url = new URL(next);
            InputStream is = url.openConnection().getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String s = br.readLine();
            while (s != null) {
                System.out.println(s);
                /*if (s.matches("^ *<source.*")) {
                    System.out.println("s = " + s);
                }*/
                s = br.readLine();
            }
        }
    }
}

class CopyB {
    public static void main(String[] args) throws IOException {
        File o = new File("/home/zhc/Downloads/ts/youku/XNTUxMDQ1Nzk2/o.mp4");
        OutputStream os = new FileOutputStream(o);
        for (int i = 1; i < 32; i++) {
            File file = new File("/home/zhc/Downloads/ts/youku/XNTUxMDQ1Nzk2/" + i);
            InputStream is = new FileInputStream(file);
            int readLen;
            byte[] buf = new byte[1024];
            while (true) {
                if ((readLen = is.read(buf)) != -1) {
                    os.write(buf, 0, readLen);
                    os.flush();
                } else break;
            }
            is.close();
        }
    }
}

class S {
    public static void main(String[] args) throws IOException {
        FileU.FileCopy(new File("/home/zhc/Downloads/ts/youku/XNTUxMDQ1Nzk2/o")
                , new File("/home/zhc/Downloads/ts/youku/XNTUxMDQ1Nzk2/o.mp4"), 34);
    }
}