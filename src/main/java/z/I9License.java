package z;

import pers.zhc.u.common.ReadIS;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class I9License {
    public static void main(String[] args) throws IOException {
        for (int i = 144653; ; i++) {
            System.out.println(getLicense("IDEXZRGPWE" + i));
            break;
        }
    }

    public static String getLicense(String code) throws IOException {
        URL url = new URL(String.format("http://pc.xz.cn/index.php?ac=ajax_checkCode&bianhao=%s&yzm=undefined&is_domain=0"
                , code));
        InputStream inputStream = url.openStream();
        StringBuilder sb = new StringBuilder();
        new ReadIS(inputStream, StandardCharsets.UTF_8).read(sb::append);
        String s = sb.toString();
        String[] split = s.split("</textarea>");
        char[] chars = split[split.length - 1].toCharArray();
        StringBuilder sb2 = new StringBuilder();
        for (int i = chars.length - 1; i > 0; --i) {
            if (chars[i] == '>') break;
            sb2.insert(0, chars[i]);
        }
        inputStream.close();
        return sb2.toString();
    }
}