package com.zhc.u;


import com.zhc.u.common.Documents;

import java.io.*;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings({"unused", "WeakerAccess"})
public class FileU {
    public static FileU o = new FileU();

    /**
     * @param inputStream     Stream_a
     * @param to_outputStream to: Stream_b
     */
    public static void StreamWrite(InputStream inputStream, OutputStream to_outputStream) throws IOException {
        StreamWrite_extracted(inputStream, to_outputStream);
    }

    /**
     * @param inputStream     Stream_a
     * @param to_outputStream to: Stream_b
     * @param skip            skip (byte(s))
     */
    public static void StreamWrite(InputStream inputStream, OutputStream to_outputStream, long skip) throws IOException {
        System.out.println("skip: " + inputStream.skip(skip));
        StreamWrite_extracted(inputStream, to_outputStream);
    }

    private static void StreamWrite_extracted(InputStream inputStream, OutputStream to_outputStream) throws IOException {
        while (true) {
            byte[] b = new byte[1024];
            int readLen;
            if ((readLen = inputStream.read(b)) != -1) {
                to_outputStream.write(b, 0, readLen);
            } else break;
        }
        to_outputStream.flush();
    }

    /**
     * _TODO write
     *
     * @param inputStream     Stream_a
     * @param to_outputStream to: Stream_b
     * @param skip            skip
     * @param len             the number of bytes to write.
     * @throws IOException IOException
     */
    public static void StreamWrite(InputStream inputStream, OutputStream to_outputStream, long skip, long len) throws IOException {
        System.out.println("skip: " + inputStream.skip(skip));
        long ardRead = 0L;
        while (true) {
            byte[] b = new byte[1024];
            int readLen;
            if ((readLen = inputStream.read(b)) != -1) {
                ardRead += readLen;
                boolean bo = ardRead > len;
                to_outputStream.write(b, 0, bo ? (int) (len % 1024L) : readLen);
                if (bo) break;
            } else break;
        }
        to_outputStream.flush();

    }

    public static OutputStream StreamParse(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        StreamWrite(in, baos);
        return baos;
    }

    public static InputStream StreamParse(OutputStream out) {
        ByteArrayOutputStream baos = (ByteArrayOutputStream) out;
        return new ByteArrayInputStream(baos.toByteArray());
    }


    /**
     * 文件复制 可选择覆盖或不覆盖
     *
     * @param srcFile  第一个文件
     * @param destFile 需要复制到的文件
     * @return 是否成功完成
     * @throws IOException 流异常
     */
    public static boolean FileCopy(java.io.File srcFile, java.io.File destFile) throws IOException {
        boolean r1 = true, r2 = true, r3 = true;
        if (srcFile.exists()) {
            if (!destFile.exists()) r1 = destFile.createNewFile();
            else r2 = destFile.delete();
            InputStream is = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);
            StreamWrite(is, fos);
        } else {
            r3 = false;
        }
        return r1 && r2 && r3;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static boolean FileCopy(java.io.File srcFile, java.io.File destFile, long skip) throws IOException {
        boolean r1 = true, r2 = true, r3 = true;
        if (srcFile.exists()) {
            if (!destFile.exists()) r1 = destFile.createNewFile();
            else r2 = destFile.delete();
            InputStream is = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);
            StreamWrite(is, fos, skip);
        } else {
            r3 = false;
        }
        return r1 && r2 && r3;
    }

    /**
     * 文件复制 可选择覆盖或不覆盖
     *
     * @param srcFile   第一个文件
     * @param destFile  需要复制到的文件
     * @param overwrite 是否覆盖
     * @return 是否成功完成
     * @throws IOException 流异常
     */
    public static boolean FileCopy(java.io.File srcFile, java.io.File destFile, boolean overwrite) throws IOException {
        boolean r1 = true, r2 = true, r3 = true;
        if (srcFile.exists()) {
            if (!destFile.exists()) r1 = destFile.createNewFile();
            else if (!overwrite) r2 = destFile.delete();
            else r2 = destFile.delete();
            InputStream is = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);
            StreamWrite(is, fos);
        } else {
            r3 = false;
        }
        return r1 && r2 && r3;
    }

    public static void DownloadWeb(URL url, OutputStream to_outputStream) throws IOException {
        StreamWrite(url.openConnection().getInputStream(), to_outputStream);
    }

    public static void DownloadWeb(String url, OutputStream to_outputStream) throws IOException {
        URL u = new FileU().StrToUrl(url);
        StreamWrite(u.openConnection().getInputStream(), to_outputStream);
    }

    public static InputStream DownloadWeb(URL url) throws IOException {
        return url.openConnection().getInputStream();
    }

    public static InputStream DownloadWeb(String url) throws IOException {
        URL u = new FileU().StrToUrl(url);
        return u.openConnection().getInputStream();
    }

    public static void DownloadWeb(URL url, java.io.File file) throws IOException {
        OutputStream fos = new FileOutputStream(file, false);
        StreamWrite(url.openConnection().getInputStream(), fos);
    }

    public static void DownloadWeb(String url, java.io.File file) throws IOException {
        URL u = new FileU().StrToUrl(url);
        OutputStream fos = new FileOutputStream(file, false);
        StreamWrite(u.openConnection().getInputStream(), fos);
    }

    public URL StrToUrl(String s) throws MalformedURLException {
        String s1 = s.split("\\.")[0];
        return new URL(!s1.matches(".*http.*") ? ("http://" + s) : s);
    }

    public URL StrToUrl(String s, @Documents.Nullable URL url) throws MalformedURLException {
        String s1 = url.toString();
        int i = s1.lastIndexOf('/');
        String substring = s1.substring(0, i + 1);
        return new URL(s.matches(".*http.*://.*") ? s : substring + s);
    }

    public static void ReadServletInputStreamToFileIS(InputStream in, OutputStream to_outputStream, long fileSize) throws IOException {
        FileU o = new FileU();
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream(), baos2 = new ByteArrayOutputStream();
        StreamCopy(in, baos1, baos2);
        InputStream is1 = StreamParse(baos1);
        InputStream is2 = StreamParse(baos2);
        long skip = o.find0a0d0a0d(is1);
        StreamWrite(is2, to_outputStream, skip, fileSize);
    }

    public static void StreamCopy(InputStream source, ByteArrayOutputStream dest1, ByteArrayOutputStream dest2) throws IOException {
        while (true) {
            int readLen;
            byte[] b = new byte[1024];
            if ((readLen = source.read(b)) != -1) {
                dest1.write(b, 0, readLen);
                dest2.write(b, 0, readLen);
            } else break;
        }
        dest1.flush();
        dest2.flush();
    }

    private long find0a0d0a0d(InputStream inputStream) throws IOException {
        long skip = 0;
        byte[] b;
        w:
        while (true) {
            b = new byte[1024];
            if (inputStream.read(b) != -1) {
                for (int i = 0; i < b.length - 3; i++) {
                    if (b[i] == 0x0D && b[i + 1] == 0x0A && b[i + 2] == 0x0D && b[i + 3] == 0x0A) {
                        skip = i + 4;
                        break w;
                    }
                }
            } else break;
        }
        return skip;
    }

    public static String getMD5String(java.io.File file) throws IOException {
        InputStream is = new FileInputStream(file);
        return getMD5String(is);
    }

    public static String getMD5String(InputStream inputStream) throws IOException {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] b = new byte[1024];
        while (true) {
            int readLen;
            if ((readLen = inputStream.read(b)) != -1) {
                assert md != null;
                md.update(b, 0, readLen);
            } else break;
        }
        inputStream.close();
        assert md != null;
        return new Base128(new BigInteger(1, md.digest()).toString(16)).NumStr_lenTo(32);
//        return DigestUtils.md5Hex(inputStream);
    }

    public boolean[] FileMove(java.io.File srcFile, java.io.File destFile) throws IOException {
        boolean[] b = new boolean[2];
        b[0] = FileCopy(srcFile, destFile);
        b[1] = srcFile.delete();
        return b;
    }

    public String getFileExtension(java.io.File f) {
        String N = f.getName();
        return N.substring(N.lastIndexOf(".") + 1);
    }

    public String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public String getFileName(String file) {
        if (!file.matches(".*\\..*")) return file;
        int indexOf = file.lastIndexOf('.');
        return file.substring(0, indexOf);
    }

    public String changeFileExtension(String fileName, String newFileExtension) {
        return this.getFileName(fileName) + "." + newFileExtension;
    }

    public String fileOpen_String_OneLine(java.io.File file, String charset) throws IOException {
        String r;
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, charset);
        BufferedReader br = new BufferedReader(isr);
        r = br.readLine();
        fis.close();
        isr.close();
        br.close();
        return r;
    }

    public void renameFile_SameFileName(java.io.File file, java.io.File To_dest) {
        if (To_dest.exists()) {
            long i = 0;
            java.io.File dest;
            while (true) {
                i++;
                dest = new java.io.File(To_dest.getPath() + " (" + i + ")");
                if (!dest.exists()) {
                    System.out.println("ren: " + To_dest.renameTo(dest));
                    break;
                }
            }
        } else {
            System.out.println("ren: " + file.renameTo(To_dest));
        }
    }

    public java.io.File creatFile_SameFileName(java.io.File file) throws IOException {
        java.io.File r;
        if (file.exists()) {
            long i = 0;
            java.io.File t;
            while (true) {
                i++;
                t = new java.io.File(file.getPath() + " (" + i + ")");
                if (!t.exists()) {
                    System.out.println("creatFile: " + t.createNewFile());
                    r = t;
                    break;
                }
            }
        } else {
            System.out.println("creatFile: " + file.createNewFile());
            r = file;
        }
        return r;
    }

    public static class TraversalFile {
        private java.io.File dic;

        public TraversalFile(String dic) {
            this.dic = new java.io.File(dic);
        }

        public TraversalFile(java.io.File dic) {
            this.dic = dic;
        }

        public void Do(TraversalFileDo target) {
            List<java.io.File> ds = new ArrayList<>();
            ds.add(dic);
            int i = 0;
            while (true) {
                try {
                    dic = ds.get(i);
                } catch (IndexOutOfBoundsException ignored) {
                    break;
                }
                java.io.File[] As = dic.listFiles();
                if (As != null) {
                    for (java.io.File f : As) {
                        target.a(f);
                        if (f.isFile()) {
                            target.f(f);
                        } else if (f.isDirectory()) {
                            ds.add(f);
                            target.d(f);
                        }
                    }
                }
                i++;
            }
        }
    }

    public interface TraversalFileDo {
        void f(java.io.File f);

        void d(java.io.File d);

        void a(java.io.File a);
    }

    public static class FindDuplicateFile {
        final public static int In_a_Folder = 0;
        final public static int Traverse_All = 1;
        private static String p;

        public static void main(String[] args) {
            System.out.println("InputP");
            p = new Scanner(System.in).next();
            new FindDuplicateFile().Find(new java.io.File(p), FindDuplicateFile.Traverse_All);
        }

        public void Find(java.io.File path, int method) {
            List<java.io.File> fileArrayList = new ArrayList<>();
            if (method == In_a_Folder) {
                java.io.File[] list = path.listFiles();
                fileArrayList = new ArrayList<>();
                if (list != null) {
                    for (java.io.File f : list) {
                        if (f.isFile()) fileArrayList.add(f);
                    }
                }
            } else if (method == Traverse_All) {
                final List<java.io.File> finalFileArrayList = fileArrayList;
                new TraversalFile(path).Do(new TraversalFileDo() {
                    @Override
                    public void f(java.io.File f) {
                        finalFileArrayList.add(f);
                    }

                    @Override
                    public void d(java.io.File d) {

                    }

                    @Override
                    public void a(java.io.File a) {

                    }
                });
            }
            java.io.File[] files = fileArrayList.toArray(new java.io.File[0]);
            String[] md5 = new String[fileArrayList.size()];
            /*//noinspection unchecked
            new Arr.SeparateArr<>(files, 1).separate((Arr.SeparateArrDo) A -> {
                String[] pMd5 = new FindDuplicateFileThread().Main(A);
                System.out.println(Arrays.toString(pMd5));
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });*/
            for (int i = 0; i < files.length; i++) {
                try {
                    String md5String = getMD5String(files[i]);
                    md5[i] = md5String;
                    System.out.println(String.format("%d..%s", files.length - i, md5String));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("ok-----------------------------");
            BufferedWriter bw = null;
            try {
                java.io.File out = new java.io.File(new java.io.File(p).getCanonicalFile() + "/chong.txt");
                if (!out.exists()) System.out.println(out.createNewFile());
                FileOutputStream fos = new FileOutputStream(out, false);
                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                bw = new BufferedWriter(osw);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < md5.length; i++) {
                String cMd5 = md5[i];
                for (int j = 0; j < md5.length; j++) {
                    if (j != i) {
                        if (cMd5.equals(md5[j])) {
                            String w = files[i] + "\t" + files[j] + "\t" + cMd5 + "\r\n";
                            System.out.println(w);
                            try {
                                if (bw != null) {
                                    bw.write(w);
                                    System.out.println("bw = " + bw);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            try {
                if (bw != null) {
                    bw.flush();
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readISR(InputStreamReader isr) throws IOException {
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        while (s != null) {
            System.out.println(s);
            s = br.readLine();
        }
        br.close();
    }

}

/*
class FindDuplicateFileThread implements Runnable {
    private Thread t;
    private int i;
    private String fileName;
    private static CountDownLatch latch;
    private static String[] md5;

    @Override
    public void run() {
        FileU f = new FileU(fileName);
        try {
            md5[this.i] = com.zhc.u.FileU.getMD5String(f);
            System.out.println("md5[this.i] = " + md5[this.i]);
            latch.countDown();
            Thread.sleep(50);
        } catch (NoSuchAlgorithmException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void S() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    public String[] Main(Object[] objects) {
        int length = objects.length;
        System.out.println(length + "---------------------------------");
        md5 = new String[length];
        latch = new CountDownLatch(length);
        for (int i = 0; i < length; i++) {
            String fN = String.valueOf(objects[i]);
            new FindDuplicateFileThread(i, fN).S();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return md5;
    }

    private FindDuplicateFileThread(int i, String fileName) {
        this.i = i;
        this.fileName = fileName;
    }

    FindDuplicateFileThread() {
    }
}
*/