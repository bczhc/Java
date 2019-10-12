package pers.zhc.u.common;

import pers.zhc.u.FileU;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class MultipartUploader {
    /**
     *
     * @param urlStr url
     * @param is     inputStream
     * @return 返回response数据
     */
    public static String formUpload(String urlStr, @Documents.Nullable byte[] headByte, InputStream is) throws IOException {
        String res;
        HttpURLConnection conn = null;
        // boundary就是request头和上传文件内容的分隔符
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            // conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            if (headByte != null) {
                out.write(headByte);
                out.flush();
            }
            // file
            FileU.StreamWrite(is, out);
            is.close();
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
        } catch (IOException e) {
            if (conn != null) {
                conn.disconnect();
            }
            throw e;
        }
        return res;
    }
}
