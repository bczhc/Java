package pers.zhc.u.util;

import pers.zhc.u.common.Documents;
import pers.zhc.u.common.ReadIS;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Connection {
    public static URLConnection post(URL url, @Documents.Nullable Map<String, String> params, @Documents.Nullable Map<String, String> requsetProperty) throws IOException {
        URLConnection connection = url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        if (requsetProperty != null) {
            requsetProperty.forEach(connection::setRequestProperty);
        }
        OutputStream os = connection.getOutputStream();
        if (params != null) {
            String pStr = mapParamsToString(params);
            os.write(pStr.getBytes(StandardCharsets.UTF_8));
            os.flush();
        }
        os.close();
        return connection;
    }

    public static URLConnection post(URL url, @Documents.Nullable String fromData, @Documents.Nullable Map<String, String> requsetProperty) throws IOException {
        URLConnection connection = url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        if (requsetProperty != null) {
            requsetProperty.forEach(connection::setRequestProperty);
        }
        OutputStream os = connection.getOutputStream();
        if (fromData != null) {
            os.write(fromData.getBytes(StandardCharsets.UTF_8));
            os.flush();
        }
        os.close();
        return connection;
    }

    public static URLConnection get(URL url, @Documents.Nullable Map<String, String> params, @Documents.Nullable Map<String, String> requestProperty) throws IOException {
        String paramsToString = "";
        if (params != null) {
            paramsToString = mapParamsToString(params);
        }
        url = new URL(url.toString() + (params == null ? "" : "?") + paramsToString);
        URLConnection connection = url.openConnection();
        if (requestProperty != null) {
            requestProperty.forEach(connection::setRequestProperty);
        }
        return connection;
    }

    public static String mapParamsToString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        params.forEach((s, s2) -> {
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
        return sb.toString();
    }

    public static String getCookiesString(URLConnection connection) {
        StringBuilder r = new StringBuilder();
        List<String> cookies = connection.getHeaderFields().get("Set-Cookie");
        if (cookies != null) {
            for (String cookie : cookies) {
                String cookieContentString;
                int indexOf = cookie.indexOf(';');
                if (indexOf == -1) cookieContentString = cookie;
                else cookieContentString = cookie.substring(0, indexOf);
                r.append(cookieContentString).append("; ");
            }
        }
        return r.toString();
    }

    public static Map<String, String> stringParams2Map(String s) {
        Map<String, String> map = new HashMap<>();
        String[] split = s.split("\n");
        for (String o : split) {
            int index = o.indexOf(':');
            map.put(o.substring(0, index), o.substring(index + 2));
        }
        return map;
    }

    public static Map<String, String> cookie2Map(String s) {
        Map<String, String> map = new HashMap<>();
        String[] split = s.split("; ");
        for (String s1 : split) {
            String[] split1 = s1.split("=");
            map.put(split1[0], split1.length == 1 ? "" : split1[1]);
        }
        return map;
    }

    public static String getString(URLConnection connection) throws IOException {
        return getString(connection, StandardCharsets.UTF_8);
    }

    public static String getString(URLConnection connection, Charset charsets) throws IOException {
        InputStream is = connection.getInputStream();
        String s = ReadIS.readToString(is, charsets);
        is.close();
        return s;
    }
}
