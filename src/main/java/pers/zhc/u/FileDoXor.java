package pers.zhc.u;


import java.io.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * &#x7b80;&#x5355;&#x7684;&#x5f02;&#x6216;&#x52a0;&#x5bc6;&#x89e3;&#x5bc6;&#x901a;&#x7528;
 *
 * @author zhc
 */
public class FileDoXor {
    private static final FileDoXor o = new FileDoXor();

    FileDoXor() {
    }

    public static void main(String[] args) throws Exception {
        File file = null, dest = null;
        byte[] k = new byte[0];
        String s;
        for (int i = 0; i < args.length; i++) {
            s = args[i];
            switch (s.toLowerCase()) {
                case "-file":
                    file = new File(args[i + 1]);
                    break;
                case "-dest":
                    dest = new File(args[i + 1]);
                    break;
                case "-keybytes":
                    k = o.StringSplitToBytes(args[i + 1]);
                    break;
                case "-keytext":
                    k = args[i + 1].getBytes();
                    break;
            }
        }
        if (file == null || dest == null || Arrays.equals(k, new byte[0])) {
            System.out.println("\u7b80\u5355\u7684\u5f02\u6216\u52a0\u5bc6\u3001\u89e3\u5bc6\n" +
                    "Command [-file filePath] [-dest filePath] [-keyBytes bytes] [keyText string]");
            return;
        }
        DoXor(new FileInputStream(Objects.requireNonNull(file))
                , new FileOutputStream(Objects.requireNonNull(dest)), k);
    }

    public static void DoXor(InputStream in, OutputStream destOut, byte[] key) throws IOException {
        byte[] b, r;
        int available;
        while (true) {
            available = in.available();
            if (available == 0) break;
            b = new byte[available < 1024 ? available : 1024];
            if (in.read(b) != -1) {
                r = o.bytesDo(b, key);
                destOut.write(r);
            } else break;
        }
        destOut.flush();
        in.close();
        destOut.close();
    }

    /*public static void DoXor(InputStream in, OutputStream destOut, InputStream keyStream) throws IOException {//todo keyFile
        long inS = in.available(), keyS = keyStream.available();
        byte[] b = new byte[1024];
        byte[] k = new byte[1024];
        if (keyS >= inS) {//TODO
            int readLen, readLen_k;
            while (true) {
                if (((readLen = in.read(b)) != -1)) {
                    readLen_k =  keyStream.read(k);
                    destOut.write(o.bytesDo(b, k));
                } else break;
            }
            destOut.flush();
            destOut.close();
        } else {

        }
    }*/

    private byte[] bytesDo(byte[] bytes, byte[] key) {
        int keyLen = key.length;
        int bytesLen = bytes.length;
        if (keyLen == 0) {
            return new byte[0];
        }
        byte[] r = new byte[bytesLen < 1024 ? bytesLen : 1024];
        for (int i = 0; i < r.length; i++) {
            r[i] = (byte) (bytes[i] ^ key[i % keyLen]);
        }
        return r;
    }

    public byte[] StringSplitToBytes(String s) {
        byte[] r = new byte[0];
        if (!s.matches(".*,.*")) {
            try {
                r = new byte[1];
                r[0] = Byte.parseByte(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String[] t = s.split(",");
            r = new byte[t.length];
            for (int i = 0; i < t.length; i++) {
                r[i] = Byte.parseByte(t[i].replace(" ", ""));
            }
        }
        return r;
    }
}