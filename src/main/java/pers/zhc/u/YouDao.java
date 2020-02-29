package pers.zhc.u;

import org.json.JSONArray;
import org.json.JSONObject;
import pers.zhc.u.common.Documents;
import pers.zhc.u.common.ReadIS;
import pers.zhc.u.util.MD5;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class YouDao {
    public final static String LANGUAGE_AUTO = "AUTO";
    public final static String LANGUAGE_CHINESE = "zh-CHS";
    public final static String LANGUAGE_ENGLISH = "en";
    public final static String LANGUAGE_JAPANESE = "ja";
    public final static String LANGUAGE_RUSSIAN = "ru";
    public final static String LANGUAGE_KOREAN = "ko";
    public final static String LANGUAGE_FRANCE = "fr";
    public final static String LANGUAGE_FRENCH = "fr";
    public final static String LANGUAGE_GERMAN = "de";
    public final static String LANGUAGE_SPANISH = "es";
    public final static String LANGUAGE_PORTUGUESE = "pt";
    public final static String LANGUAGE_ITALIAN = "it";
    public final static String LANGUAGE_VIETNAMESE = "vi";
    public final static String LANGUAGE_ARABIC= "ar";
    public static String translate(String str, @Documents.Nullable String languageFrom
            , @Documents.Nullable String languageTo) throws IOException {
        languageFrom = languageFrom == null ? LANGUAGE_AUTO : languageFrom;
        languageTo = languageTo == null ? LANGUAGE_AUTO : languageTo;
        long timeStamp = System.currentTimeMillis();
        int rand_int = pers.zhc.u.Random.ran_sc(0, 10);
        String appVersion = "5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36";
        String bv = MD5.md5(appVersion);
        String tsStr = String.valueOf(timeStamp);
        final String c = "Nw(nmmbP%A-r6U3EUn]Aj";
        String sign = MD5.md5("fanyideskweb" + str + tsStr + rand_int + c);
        String cookie = "OUTFOX_SEARCH_USER_ID=-744869165@121.227.138.203" +
                "; OUTFOX_SEARCH_USER_ID_NCOO=305645688.41944355" +
                "; _ntes_nnid=c6df9c9fddfee6150e1a3048322e76cd," + timeStamp +
                "; DICT_UGC=be3af0da19b5c5e6aa4e17bd8d90b28a|" +
                "; JSESSIONID=abcktOOAomqZ2aW7IRkcx" +
                "; ___rl__test__cookies=" + timeStamp;
        Map<String, String> data = new HashMap<>();
        data.put("i", str);
        data.put("from", languageFrom);
        data.put("to", languageTo);
        data.put("smartresult", "dict");
        data.put("client", "fanyideskweb");
        data.put("version", "2.1");
        data.put("ts", tsStr);
        data.put("salt", String.valueOf(timeStamp * 10 + rand_int));
        data.put("doctype", "json");
        data.put("keyfrom", "fanyi.web");
        data.put("action", "FY_BY_REALTlME");
        data.put("bv", bv);
        data.put("sign", sign);
        data.put("type", languageFrom + "2" + languageTo);
        StringBuilder sb = new StringBuilder();
        data.forEach((s, s2) -> {
            String p1 = null;
            try {
                p1 = URLEncoder.encode(s2, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (sb.length() == 0) {
                sb.append(s).append('=').append(p1);
            } else
                sb.append('&').append(s).append('=').append(p1);
        });
        String params = sb.toString();
        URL url = new URL("http://fanyi.youdao.com/translate_o?smartresult=dict&smartresult=rule");
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("Cookie", cookie);
        connection.setRequestProperty("User-Agent", "Mozilla/" + appVersion);
        connection.setRequestProperty("Referer", "http://fanyi.youdao.com/?keyfrom=dict2.index");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(params);
        bw.flush();
        bw.close();
        osw.close();
        os.close();
        InputStream is = connection.getInputStream();
        StringBuilder r = new StringBuilder();
        StringBuilder result = new StringBuilder();
        new ReadIS(is, StandardCharsets.UTF_8).read(r::append);
        JSONObject jsonObject = new JSONObject(r.toString());
        int errorCode = jsonObject.getInt("errorCode");
        if (errorCode == 0) {
            JSONArray translateResult = jsonObject.getJSONArray("translateResult");
            int length = translateResult.length();
            for (int i = 0; i < length; i++) {
                JSONArray lineResultJSONArray = translateResult.getJSONArray(i);
                int lineLength = lineResultJSONArray.length();
                for (int j = 0; j < lineLength; j++) {
                    JSONObject translateResultJSONObject = lineResultJSONArray.getJSONObject(j);
                    result.append(translateResultJSONObject.getString("tgt"));
                    if (j != lineLength - 1) result.append(' ');
                }
                if (i != length - 1)
                    result.append('\n');
            }
        } else {
            result.append("error: ").append(errorCode);
        }
        return result.toString();
    }

    @Documents.Nullable
    public static String getConstStr() {
        try {
            URL url = new URL("http://shared.ydstatic.com/fanyi/newweb/v1.0.24/scripts/newweb/fanyi.min.js");
            InputStream is = url.openStream();
            StringBuilder sb = new StringBuilder();
            new ReadIS(is).read(sb::append);
            is.close();
            String js = sb.toString();
            String t = "\"fanyideskweb\"+e+i+\"";
            int indexOf = js.indexOf(t);
            int l = t.length();
            int i = indexOf + l;
            StringBuilder c = new StringBuilder();
            while (true) {
                char charAt = js.charAt(i);
                if (charAt == '\"') break;
                c.append(charAt);
                ++i;
            }
            return c.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        String hello = translate("", YouDao.LANGUAGE_AUTO, YouDao.LANGUAGE_AUTO);
        System.out.println("hello = " + hello);
    }
}