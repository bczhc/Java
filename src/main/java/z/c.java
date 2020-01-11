package z;

import pers.zhc.u.FileU;

import java.io.*;

public class c {
    public static void main(String[] args) throws IOException {
        for (int i = 1; i < 32; i++) {
            File f = new File("/home/zhc/d/youku/downloadVideo/offlinedata/oldV_c_download/XNTUxMDQ1Nzk2/" + i);
            File o = new File("/home/zhc/d/youku/downloadVideo/offlinedata/oldV_c_download/XNTUxMDQ1Nzk2/c/" + i);
            InputStream is = new FileInputStream(f);
            OutputStream os = new FileOutputStream(o, false);
            FileU.StreamWrite(is, os, 34);
            os.close();
            is.close();
        }
    }
}