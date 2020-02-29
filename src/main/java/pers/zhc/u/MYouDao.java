package pers.zhc.u;

import org.json.JSONException;
import pers.zhc.u.common.ReadIS;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MYouDao {
    public static String translate(String text) throws IOException, JSONException {
        URL url = new URL("http://m.youdao.com/translate");
        URLConnection connection = url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestProperty("Cookie", "_yd_btn_fanyi_29=true; _yd_newbanner_day=29; DICT_UGC=be3af0da19b5c5e6aa4e17bd8d90b28a|; OUTFOX_SEARCH_USER_ID=2003957726@121.227.138.203; JSESSIONID=abc1yGRAlgCGfeqJ_apcx; OUTFOX_SEARCH_USER_ID_NCOO=1139756590.752576; YOUDAO_MOBILE_ACCESS_TYPE=0; ___rl__test__cookies=1582947431505");
        connection.setRequestProperty("Referer", "http://m.youdao.com/translate?vendor=fanyi.web");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Mobile Safari/537.36");
        Map<String, String> params = new HashMap<>();
        params.put("inputtext", "hello");
        params.put("type", "AUTO");
        String paramsString = YouDao.mapParamsToString(params);
        OutputStream os = connection.getOutputStream();
        os.write(paramsString.getBytes());
        os.flush();
        os.close();
        InputStream is = connection.getInputStream();
        StringBuilder rSB = new StringBuilder();
        new ReadIS(is, StandardCharsets.UTF_8).read(rSB::append);
        is.close();
        String r = rSB.toString();
        String s = "<ul id=\"translateResult\">";
        int indexOf = r.indexOf(s);
        int sLength = s.length();
        StringBuilder sb = new StringBuilder();
        int i = indexOf + sLength;
        while (true) {
            char charAt = r.charAt(i);
            if (charAt == '<' && r.charAt(i + 1) == '/' && r.charAt(i + 2) == 'l'
                    && r.charAt(i + 3) == 'i' && r.charAt(i + 4) == '>') {
                break;
            }
            sb.append(charAt);
            ++i;
        }
        String result = sb.toString();
        return result;
    }

    public static void main(String[] args) {
        int n = 100000;
        ExecutorService es = Executors.newFixedThreadPool(8);
        CountDownLatch latch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            int finalI = i;
            es.execute(() -> {
                try {
                    String translate = translate("hello my baby.");
                    System.out.println("translate " + finalI + " = " + translate);
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
    }
}
