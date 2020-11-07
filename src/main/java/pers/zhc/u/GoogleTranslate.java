package pers.zhc.u;

import org.json.JSONArray;
import org.json.JSONException;
import pers.zhc.u.common.ReadIS;
import pers.zhc.u.util.Connection;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class GoogleTranslate {
    private Map<String, String> requestProperty;

    public static void main(String[] args) throws IOException {
        GoogleTranslate translate = new GoogleTranslate();
    }

    public GoogleTranslate() {
        try {
            URL url = new URL("https://translate.google.cn");
            requestProperty = new HashMap<>();
            String cookie = "";
            requestProperty.put("Cookie", cookie);
            requestProperty.put("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36");
            URLConnection connection = Connection.get(url, null, requestProperty);
            requestProperty.put("Cookie", Connection.getCookiesString(connection));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String translate(String text) throws IOException, JSONException {
        URL url = new URL("https://translate.google.cn/translate_a/single");
        Map<String, String> params = new HashMap<>();
        params.put("client", "webapp");
        params.put("sl", "auto");
        params.put("tl", "zh-CN");
        params.put("hl", "zh-CN");
        params.put("dt", "at");
//        params.put("dt", "bd");
//        params.put("dt", "ex");
//        params.put("dt", "ld");
//        params.put("dt", "md");
//        params.put("dt", "qca");
//        params.put("dt", "rw");
//        params.put("dt", "rm");
//        params.put("dt", "ss");
//        params.put("dt", "t");
        params.put("source", "bh");
        params.put("ssel", "0");
        params.put("tsel", "0");
        params.put("xid", "1782844");
        params.put("kc", "1");
        params.put("tk", getToken(text));
        params.put("q", text);
        InputStream is = Connection.get(url, params, requestProperty).getInputStream();
        StringBuilder sb = new StringBuilder();
        new ReadIS(is, StandardCharsets.UTF_8).read(sb::append);
        is.close();
        JSONArray jsonArray = new JSONArray(sb.toString());
        return jsonArray.getJSONArray(5).getJSONArray(0).getJSONArray(2).getJSONArray(0).getString(0);
    }

    private String getToken(String text) {
        String js = "var bu = function(a) {\n" +
                "    return function() {\n" +
                "        return a\n" +
                "    }\n" +
                "}\n" +
                "    , cu = function(a, b) {\n" +
                "    for (var c = 0; c < b.length - 2; c += 3) {\n" +
                "        var d = b.charAt(c + 2);\n" +
                "        d = \"a\" <= d ? d.charCodeAt(0) - 87 : Number(d);\n" +
                "        d = \"+\" == b.charAt(c + 1) ? a >>> d : a << d;\n" +
                "        a = \"+\" == b.charAt(c) ? a + d & 4294967295 : a ^ d\n" +
                "    }\n" +
                "    return a\n" +
                "}\n" +
                "    , du = \"439729.1124205703\"\n" +
                "    , eu = function(a) {\n" +
                "    if (null !== du)\n" +
                "        var b = du;\n" +
                "    else {\n" +
                "        /*b = bu(String.fromCharCode(84));\n" +
                "        var c = bu(String.fromCharCode(75));\n" +
                "        b = [b(), b()];\n" +
                "        b[1] = c();\n" +
                "        b = (du = window[b.join(c())] || \"\") || \"\"*/\n" +
                "    }\n" +
                "    var d = bu(String.fromCharCode(116));\n" +
                "    c = bu(String.fromCharCode(107));\n" +
                "    d = [d(), d()];\n" +
                "    d[1] = c();\n" +
                "    c = \"\"\n" +
                "    d = b.split(\".\");\n" +
                "    b = Number(d[0]) || 0;\n" +
                "    for (var e = [], f = 0, g = 0; g < a.length; g++) {\n" +
                "        var k = a.charCodeAt(g);\n" +
                "        128 > k ? e[f++] = k : (2048 > k ? e[f++] = k >> 6 | 192 : (55296 == (k & 64512) && g + 1 < a.length && 56320 == (a.charCodeAt(g + 1) & 64512) ? (k = 65536 + ((k & 1023) << 10) + (a.charCodeAt(++g) & 1023),\n" +
                "            e[f++] = k >> 18 | 240,\n" +
                "            e[f++] = k >> 12 & 63 | 128) : e[f++] = k >> 12 | 224,\n" +
                "            e[f++] = k >> 6 & 63 | 128),\n" +
                "            e[f++] = k & 63 | 128)\n" +
                "    }\n" +
                "    a = b;\n" +
                "    for (f = 0; f < e.length; f++)\n" +
                "        a += e[f],\n" +
                "            a = cu(a, \"+-a^+6\");\n" +
                "    a = cu(a, \"+-3^+b+-f\");\n" +
                "    a ^= Number(d[1]) || 0;\n" +
                "    0 > a && (a = (a & 2147483647) + 2147483648);\n" +
                "    a %= 1E6;\n" +
                "    return c + (a.toString() + \".\" + (a ^ b))\n" +
                "};\n";
        ScriptEngine se = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            se.eval(js);
            return (String) ((Invocable) se).invokeFunction("eu", text);
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
