package z;

import pers.zhc.u.FileU;
import pers.zhc.u.common.ReadIS;

import java.io.*;
import java.net.URL;

public class MergeFile {
    public static void main(String[] args) throws IOException {
        File splitDir = new File("/home/zhc/code/code/io/bczhc.github.io/res/app/some-tools/debug/split");
        File list = new File(splitDir, "list");
        FileInputStream fis = new FileInputStream(list);
        File merged = new File(splitDir.getParentFile(), "merged.apk");
        OutputStream os = new FileOutputStream(merged);
        new ReadIS(fis).read(line -> {
            if (line != null && !line.equals(""))
                try {
                    InputStream is = new FileInputStream(new File(splitDir, line));
                    FileU.StreamWrite(is, os);
                    is.close();
                    System.out.println(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });
        fis.close();
        os.close();
        System.out.println("done.");
    }

    private static class Download {
        public static void main(String[] args) throws IOException {
            URL url = new URL("http://bczhc.gitee.io/web/res/app/some-tools/app-debug.apk");
            FileU.DownloadWeb(url, new File("./a.apk"));
        }
    }
}
