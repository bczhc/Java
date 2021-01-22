package pers.zhc.u.crawler;

import pers.zhc.u.util.Connection;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class Test {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://cn-jstz-dx-v-08.bilivideo.com/upgcxcode/86/97/279786/279786_fh1-1-30011.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEqxTEto8BTrNvN0GvT90W5JZMkX_YN0MvXg8gNEV4NC8xNEV4N03eN0B5tZlqNxTEto8BTrNvNeZVuJ10Kj_g2UB02J0mN0B5tZlqNCNEto8BTrNvNC7MTX502C8f2jmMQJ6mqF2fka1mqx6gqj0eN0B599M=&uipk=5&nbs=1&deadline=1611323311&gen=playurl&os=vcache&oi=2044726743&trid=c22f478098824887a7bb12512650398au&platform=pc&upsig=ecbf52ba71e7945df5986b4a0b8ad5e2&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&cdnid=3501&mid=382683037&orderid=0,3&agrr=0&logo=80000000");
        String header = "Accept: */*\n" +
                "Accept-Encoding: identity\n" +
                "Accept-Language: en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7\n" +
                "Cache-Control: no-cache\n" +
                "Connection: keep-alive\n" +
                "Host: rf0rpd0.cachenode.cn:25344\n" +
                "Origin: https://www.bilibili.com\n" +
                "Pragma: no-cache\n" +
                "Referer: https://www.bilibili.com/video/BV1J7411h786\n" +
                "sec-ch-ua: \"Google Chrome\";v=\"87\", \" Not;A Brand\";v=\"99\", \"Chromium\";v=\"87\"\n" +
                "sec-ch-ua-mobile: ?0\n" +
                "Sec-Fetch-Dest: empty\n" +
                "Sec-Fetch-Mode: cors\n" +
                "Sec-Fetch-Site: cross-site\n" +
                "User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36";
        URLConnection c = Connection.get(url, null, Connection.stringParams2Map(header));

        InputStream is = c.getInputStream();
        OutputStream os = new FileOutputStream("/home/zhc/a.m4s");
        long contentLength = c.getContentLengthLong();
        int read;
        long totalRead = 0;
        byte[] buffer = new byte[4096];
        while ((read = is.read(buffer)) > 0) {
            os.write(buffer, 0, read);
            os.flush();
            totalRead += read;
            if (totalRead % 1048576 == 0)
                System.out.println((((float) totalRead) / ((float) contentLength) * 100F) + "%");
        }
        System.out.println("100%");

        is.close();
        os.close();
    }
}

