package pers.zhc.u;

import org.json.JSONException;
import pers.zhc.u.common.ReadIS;
import pers.zhc.u.util.ListArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class BaiDuTranslate {
    public static String translate(String src) throws IOException, JSONException {
        URL url = new URL("https://fanyi.baidu.com/v2transapi?from=en&to=zh");
        URLConnection connection = url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        setRequestProperty(connection);
        Map<String, String> params = new HashMap<>();
        params.put("from", "en");
        params.put("to", "zh");
        params.put("query", src);
        params.put("transtype", "realtime");
        params.put("simple_means_flag", "3");
        params.put("domain", "common");
        params.put("sign", getSign(src));
        params.put("token", getToken());
        String paramsToString = YouDao.mapParamsToString(params);
        OutputStream os = connection.getOutputStream();
        os.write(paramsToString.getBytes());
        os.flush();
        os.close();
        InputStream is = connection.getInputStream();
        StringBuilder rSB = new StringBuilder();
        new ReadIS(is, StandardCharsets.UTF_8).read(rSB::append);
        is.close();
        String r = rSB.toString();
        return r;
    }

    private static String e(String str) {
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
            //TODO handle Emoji...
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
        int b = 0;
        p += S.get(b);
        p = n(p, F);
        p = n(p, D);
        p ^= s;
        if (0 > p) {
            p = (2147483647 & p) + 2147483648L;
        }
        p %= 1e6;
        return p + "." + (p ^ m);
    }

    private static long n(long r, String o) {
        for (int t = 0; t < o.length() - 2; t += 3) {
            long a = o.charAt(t + 2);
            a = a >= 'a' ? ((a - 87)) : Long.parseLong("" + ((char) a));
            a = '+' == o.charAt(t + 1) ? (r >>> (a)) : (r << (a));
            r = '+' == o.charAt(t) ? (r + a & 4294967295L) : r ^ (a);
        }
        return r;
    }

    private static String substr(String s, int startIndex, @SuppressWarnings("SameParameterValue") int length) {
        if (startIndex < 0) startIndex = 0;
        return s.substring(startIndex, startIndex + length);
    }

    private static void setRequestProperty(URLConnection connection) {
        String infos =
                "accept: */*\n" +
                        "accept-encoding: gzip, deflate, br\n" +
                        "accept-language: zh-CN,zh;q=0.9\n" +
                        "cache-control: no-cache\n" +
                        "content-length: 136\n" +
                        "content-type: application/x-www-form-urlencoded; charset=UTF-8\n" +
                        "cookie: " + getCookie() + "\n" +
                        "origin: https://fanyi.baidu.com\n" +
                        "pragma: no-cache\n" +
                        "referer: https://fanyi.baidu.com/?aldtype=16047\n" +
                        "sec-fetch-dest: empty\n" +
                        "sec-fetch-mode: cors\n" +
                        "sec-fetch-site: same-origin\n" +
                        "user-agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36\n" +
                        "x-requested-with: XMLHttpRequest";
        String[] split = infos.split("\\n");
        for (String s : split) {
            String[] split1 = s.split(": ");
            try {
                connection.setRequestProperty(split1[0], URLEncoder.encode(split1[1], "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                connection.setRequestProperty(split1[0], split1[1]);
                e.printStackTrace();
            }
        }
    }

    private static String getToken() {
        String r = null;
        try {
            URL url = new URL("https://fanyi.baidu.com/?aldtype=16047");
            URLConnection connection = url.openConnection();
            setRequestProperty(connection);
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

    private static String getSign(String text) {
        return e(text);
    }

    private static String getCookie() {
        return "BAIDUID=B597C6B751C2CC6936DFFBC4DD8F4706:FG=1";
    }

    public static void main(String[] args) throws IOException {
        String translate = translate("hello");
        System.out.println("translate = " + translate);
//        System.out.println("getToken() = " + getToken());
//        System.out.println("getSign(\"hello\") = " + getSign("hello"));
    }
}
