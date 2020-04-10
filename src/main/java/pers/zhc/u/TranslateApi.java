package pers.zhc.u;

import org.json.JSONArray;
import org.json.JSONObject;
import pers.zhc.u.common.ReadIS;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TranslateApi {
    public static String translate(String s, String srcLanguage, String targetLanguage) throws Exception {
        String rr = null;
        try {
            URLConnection connection = new URL("http://translate.google.cn/translate_a/single?client=gtx&dt=t&dj=1&sl=" + srcLanguage + "&tl=" + targetLanguage + "&q=" + URLEncoder.encode(s, "UTF-8")).openConnection();
            InputStream inputStream = connection.getInputStream();
            final String[] r = {null};
            new ReadIS(inputStream, StandardCharsets.UTF_8).read(line -> r[0] = line);
            if (r[0] == null) throw new Exception("no response received");
            JSONObject jsonObject = new JSONObject(r[0]);
            rr = ((JSONArray) jsonObject.get("sentences")).getJSONObject(0).toMap().get("trans").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rr;
    }

    public static String youdaoTranslate(String s, String srcLanguage, String targeLanguage) throws Exception {
        String type = srcLanguage.toUpperCase() + "2" + targeLanguage.toUpperCase();
        URLConnection connection = new URL("http://fanyi.youdao.com/translate?&doctype=json&type=" + type + "&i=" + URLEncoder.encode(s, "UTF-8")).openConnection();
        InputStream inputStream = connection.getInputStream();
        String rr;
        final String[] r = {null};
        new ReadIS(inputStream, StandardCharsets.UTF_8).read(line -> r[0] = line);
        if (r[0] == null) throw new Exception("no response received");
        JSONObject jsonObject = new JSONObject(r[0]);
        rr = jsonObject.getJSONArray("translateResult").getJSONArray(0).getJSONObject(0).toMap().get("tgt").toString();
        return rr;
    }
}
