package pers.zhc.u.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileMultipartUploader {
    public static String upload(String urlString, File file) throws IOException {
        byte[] bytes = file.getName().getBytes();
        InputStream is = new FileInputStream(file);
        return MultipartUploader.formUpload(urlString, bytes, is);
    }
}
