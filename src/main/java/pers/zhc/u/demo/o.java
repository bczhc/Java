package pers.zhc.u.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class o {

    public static void main(String[] args) {
//        g("http://q1.qlogo.cn/g?b=qq&nk=1075625244&s=640");
/*        for (long j = 10001L; j <= 1109237000L; j++) {
            downloadByNIO2("http://users.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg?uins=" + j, "./headImg", "test1.txt");
        }*/
        while (true) {
            g("http://users.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg?uins=1109236592");
        }
    }

    private static void g(String url) {
        try {
            URL u = new URL(url);
            InputStream is = u.openStream();
            InputStreamReader isr = new InputStreamReader(is, "GBK");
//            c.isr(isr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void downloadByNIO2(String url, String saveDir, String fileName) {
        try (InputStream ins = new URL(url).openStream()) {
            Path target = Paths.get(saveDir, fileName);
            Files.createDirectories(target.getParent());
            Files.copy(ins, target, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
