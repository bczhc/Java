import org.junit.Test;
import pers.zhc.u.FileU;
import pers.zhc.u.common.ReadIS;
import pers.zhc.u.util.Connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author bczhc
 */
public class YouKuM3u8 {
    @Test
    public void test() throws MalformedURLException {
        final URL m3u8Url = new URL("https://valipl.cp31.ott.cibntv.net/6572D8B8D07387171ABCA265D/03000700005E75510EB7CE935B7125CB364C20-1FC2-4858-B517-3B4215D5013D-1-114.m3u8?ccode=0502&duration=300&expire=18000&psid=b7f9b32e36528573816d4dcbc366b7df&ups_client_netip=b46a0ec0&ups_ts=1585751732&ups_userid=491911262&utid=K6kFFxTGbV8CAbRqDsCpDhRV&vid=XNDAyMjQ3OTY0MA%3D%3D&vkey=B3c75dba2c34c33bf022b4117e105dec5&sm=1&operate_type=1&dre=u37&si=73&eo=0&dst=1&iv=0&s=8f1220555a564cd8a2c0&type=mp4hd2v3&bc=2");
        final List<URL> urlList = resolveM3u8List(m3u8Url);
        ExecutorService es = Executors.newFixedThreadPool(8);
        final File dir = new File("./tsDownload");
        if (!dir.exists()) {
            System.out.println("dir.mkdirs() = " + dir.mkdirs());
        }
        List<URL> requireNonNull = Objects.requireNonNull(urlList);
        final int size = requireNonNull.size();
        CountDownLatch latch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            URL url = requireNonNull.get(i);
            int finalI = i;
            es.execute(() -> {
                final File file = new File(dir, String.valueOf(finalI));
                try {
//                    FileU.download(Connection.get(url, null, null), file, null, null);
                    FileU.DownloadWeb(url, file);
                    System.out.println(finalI);
                } catch (IOException e) {
                    e.printStackTrace();
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
        System.out.println("download done");
        System.out.println("merge...");
        merge();
        System.out.println("merge done");
    }

    private void merge() {
        final File dir = new File("./tsDownload");
        final File mergeFile = new File("./tsDownload/merge.ts");
        OutputStream os = null;
        InputStream is = null;
        try {
            os = new FileOutputStream(mergeFile, false);
            File[] files = dir.listFiles((dir1, name) -> name.matches("^[0-9]+$"));
            files = files == null ? new File[0] : files;
            for (int i = 0; i < files.length; i++) {
                final File file = new File(dir, String.valueOf(i));
                is = new FileInputStream(file);
                try {
                    FileU.StreamWrite(is, os);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<URL> resolveM3u8List(URL m3u8Url) {
        List<URL> urlList = new ArrayList<>();
        InputStream inputStream = null;
        try {
            final URLConnection connection = Connection.get(m3u8Url, null , null);
            inputStream = connection.getInputStream();
            new ReadIS(inputStream).read(line -> {
                if (line.matches("^[^#].*$")) {
                    try {
                        urlList.add(new URL(line));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            });
            return urlList;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
