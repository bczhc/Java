package z;

import java.io.*;

@SuppressWarnings("DuplicatedCode")
public class SplitFile {
    public static void main(String[] args) throws IOException {
        long perFileLength = 50 * 1024L;
        File dir = new File("/home/zhc/code/code/io/bczhc.github.io/res/app/some-tools/debug");
        File splitDir = new File(dir, "split");
        if (!splitDir.exists()) {
            System.out.println("splitDir.mkdir() = " + splitDir.mkdir());
        }
        File file = new File(dir, "app-debug.apk");
        File list = new File(splitDir, "list");
        OutputStream listOS = new FileOutputStream(list, false);
        OutputStreamWriter osw = new OutputStreamWriter(listOS);
        BufferedWriter bw = new BufferedWriter(osw);
        long length = file.length();
        long a = length / perFileLength;
        int b = (int) (length % perFileLength);
        InputStream is = new FileInputStream(file);
        for (int i = 0; i < a; i++) {
            File f = new File(splitDir, String.valueOf(i));
            OutputStream os = new FileOutputStream(f, false);
            for (int j = 0; j < 50; j++) {
                byte[] buffer = new byte[1024];
                System.out.println("is.read(buffer) = " + is.read(buffer));
                os.write(buffer);
                os.flush();
            }
            os.close();
            String name = f.getName();
            System.out.println(name);
            bw.write(name);
            bw.newLine();
            bw.flush();
        }
        if (b != 0) {
            File f = new File(splitDir, String.valueOf(a));
            OutputStream os = new FileOutputStream(f, false);
            byte[] buffer = new byte[b];
            System.out.println("is.read(buffer) = " + is.read(buffer));
            os.write(buffer);
            os.flush();
            os.close();
            String name = f.getName();
            System.out.println(name);
            bw.write(name);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        osw.close();
        listOS.close();
        is.close();
        System.out.println("done.");
    }
}
