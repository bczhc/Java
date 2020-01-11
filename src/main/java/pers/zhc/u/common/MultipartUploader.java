package pers.zhc.u.common;

import org.jetbrains.annotations.NotNull;
import pers.zhc.u.FileU;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class MultipartUploader {
    /**
     * @param urlStr   url
     * @param headByte head
     * @param is       inputStream
     * @return response data
     * 如果head != null，将会上传head+null字节+is数据
     * 否则上传is数据
     * 其中{0}作为头和数据的分隔符，便于后台处理
     */
    public static String formUpload(String urlStr, @Documents.Nullable byte[] headByte, InputStream is) throws IOException {
        String res;
        HttpURLConnection conn = null;
        // boundary就是request头和上传文件内容的分隔符
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            OutputStream out = getOutputStream(conn);
            headBytePut(headByte, out);
            // file
            FileU.StreamWrite(is, out);
            is.close();
            res = left(conn, out);
        } catch (IOException e) {
            if (conn != null) {
                conn.disconnect();
            }
            throw e;
        }
        return res;
    }

    private static void headBytePut(@Documents.Nullable byte[] headByte, OutputStream out) throws IOException {
        if (headByte != null) {
            out.write(new byte[]{0});
            out.write(headByte);
            out.flush();
        }
    }

    private static String left(HttpURLConnection conn, OutputStream out) throws IOException {
        String res;
        out.close();
        // 读取返回数据
        StringBuilder strBuf = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            strBuf.append(line).append("\n");
        }
        res = strBuf.toString();
        reader.close();
        return res;
    }

    public static String formUpload(String urlStr, @Documents.Nullable byte[] headByte, @Documents.NotNull byte[] data) throws IOException {
        String res;
        HttpURLConnection conn = null;
        // boundary就是request头和上传文件内容的分隔符
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            OutputStream out = getOutputStream(conn);
            headBytePut(headByte, out);
            // file
            out.write(data);
            res = left(conn, out);
        } catch (IOException e) {
            if (conn != null) {
                conn.disconnect();
            }
            throw e;
        }
        return res;
    }

    @NotNull
    private static OutputStream getOutputStream(HttpURLConnection conn) throws IOException {
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(30000);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Connection", "Keep-Alive");
        // conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
        return new DataOutputStream(conn.getOutputStream());
    }
}
