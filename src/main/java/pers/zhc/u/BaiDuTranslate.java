package pers.zhc.u;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pers.zhc.u.common.ReadIS;
import pers.zhc.u.util.ListArray;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class BaiDuTranslate {
    private String cookie = "";
    private String token;

    public BaiDuTranslate() {
        getToken();
        this.token = getToken();
    }

    public static void main(String[] args) throws IOException {
        BaiDuTranslate baiDuTranslate = new BaiDuTranslate();
        String translate = baiDuTranslate.translate("try", "en", "zh");
        System.out.println("translate = " + translate);
    }

    public String translate(String src, String languageFrom, String languageTo) throws IOException, JSONException {
        URL url = new URL("https://fanyi.baidu.com/v2transapi");
        URLConnection connection = url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        Map<String, String> params = new HashMap<>();
        params.put("from", languageFrom);
        params.put("to", languageTo);
        params.put("query", src);
        params.put("transtype", "realtime");
        params.put("simple_means_flag", "3");
        params.put("domain", "common");
        params.put("sign", getSign(src));
        params.put("token", token);
        String paramsToString = YouDao.mapParamsToString(params);
        setRequestProperty(connection);
        OutputStream os = connection.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(paramsToString);
        bw.flush();
        bw.close();
        osw.close();
        os.close();
        InputStream is = connection.getInputStream();
        StringBuilder rSB = new StringBuilder();
        new ReadIS(is, StandardCharsets.UTF_8).read(rSB::append);
        is.close();
        JSONObject jsonObject = new JSONObject(rSB.toString());
        JSONArray jsonArray = jsonObject.getJSONObject("trans_result").getJSONArray("data");
        return jsonArray.getJSONObject(0).getString("dst");
    }

    private String e(String str) {
        String i = "320305.131321201";
        String r = str;
        boolean o = r.matches("[\\uD800-\\uDBFF][\\uDC00-\\uDFFF]");
        //noinspection StatementWithEmptyBody
        if (!o) {
            int t = r.length();
            if (t > 30) {
                int floor = t / 2;
                r = substr(r, 0, 10) + substr(r, floor - 5, 10) + substr(r, -10, 10);
            }
        } else {
            //TODO handle emoji...
        }
//        String l = "" + ((char) 103) + ((char) 116) + ((char) 107);
        String[] d = i.split("\\.");
        int m = Integer.parseInt(d[0]);
        int s = Integer.parseInt(d[1]), c = 0, v = 0;
        ListArray<Integer> S = new ListArray<>();
        for (; v < r.length(); ++v) {
            int A = r.charAt(v);
            if (128 > A) {
                S.set(c++, A);
            } else {
                if (2048 > A) {
                    S.set(c++, A >> 6 | 192);
                } else {
                    if (55296 == (64512 & A)) {
                        if (v + 1 < r.length()) {
                            if (56320 == (64512 & r.charAt(v + 1))) {
                                A = 65536 + ((1023 & A) << 10) + (1023 & r.charAt(++v));
                                S.set(c++, A >> 18 | 240);
                                S.set(c++, A >> 12 & 63 | 128);
                            } else {
                                S.set(c++, A >> 12 | 224);
                                S.set(c++, A >> 6 & 63 | 128);
                            }
                        }
                    }
                    S.set(c++, 63 & A | 128);
                }
            }
        }
        long p = m;
        String F = new String(new char[]{43, 45, 97, 94, 43, 54});
        String D = new String(new char[]{43, 45, 51, 94, 43, 98, 43, 45, 102});
        int sSize = S.size();
        for (int b = 0; b < sSize; b++) {
            p += S.get(b);
            p = n(p, F);
        }
        p = n(p, D);
        p ^= s;
        if (0 > p) {
            p = (2147483647 & p) + 2147483648L;
        }
        p %= 1e6;
        return p + "." + (p ^ m);
    }

    private long n(long r, String o) {
        for (int t = 0; t < o.length() - 2; t += 3) {
            long a = o.charAt(t + 2);
            a = a >= 'a' ? ((a - 87)) : Long.parseLong("" + ((char) a));
            a = '+' == o.charAt(t + 1) ? (r >>> (a)) : (r << (a));
            r = '+' == o.charAt(t) ? (r + a & 4294967295L) : r ^ (a);
        }
        return r;
    }

    private String substr(String s, int startIndex, @SuppressWarnings("SameParameterValue") int length) {
        if (startIndex < 0) startIndex = s.length() + startIndex;
        return s.substring(startIndex, startIndex + length);
    }

    private void setRequestProperty(URLConnection connection) {
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
        connection.setRequestProperty("Cookie", cookie);
    }

    private String getToken() {
        String r = null;
        try {
            URL url = new URL("https://fanyi.baidu.com");
            URLConnection connection = url.openConnection();
            setRequestProperty(connection);
            String t = connection.getHeaderField("set-cookie");
            if (t != null) {
                t = extractCookie(t);
                cookie = t;
            }
            InputStream is = connection.getInputStream();
            final String[] l = {null};
            new ReadIS(is, StandardCharsets.UTF_8).read(line -> {
                if (line.matches(" *token: '.*")) {
                    l[0] = line;
                }
            });
            is.close();
            int indexOf = l[0].indexOf('\'');
            int i = indexOf + 1;
            StringBuilder sb = new StringBuilder();
            while (l[0].charAt(i) != '\'') {
                sb.append(l[0].charAt(i));
                ++i;
            }
            r = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return r;
    }

    private String extractCookie(String t) {
        int indexOf = t.indexOf(';');
        return t.substring(0, indexOf);
    }

    private String getSign(String text) {
        return e(text);
    }
}