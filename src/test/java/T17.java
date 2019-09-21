import pers.zhc.u.TranslateApi;
import pers.zhc.u.common.ReadIS;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class T17 {
    public static void main(String[] args) throws Exception {
        InputStream is = new FileInputStream(new File("/home/zhc/code/code/Android/QMCFLAC/app/src/main/res/values/strings.xml"));
        new ReadIS(is, StandardCharsets.UTF_8).read(line -> {
            if (line.matches(" *<string.*") && !line.matches(".*translatable=\"false\".*")) {
                Pattern pattern = Pattern.compile(">.*<");
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String s = matcher.group();
                    s = s.substring(1, s.length() - 1);
                    String translate = null;
                    try {
                        translate = TranslateApi.youdaoTranslate(s, "zh_cn", "en");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(translate);
                }
            }
        });
        is.close();
    }
}
