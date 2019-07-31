package Z;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class SplitText {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Command [sourceFile] | \n" +
                    "Command [sourceFile] [Charset]");
        } else {
            String charset = args.length == 2 ? args[1] : "UTF-8";
            File f = new File(args[0]);
            String splitDir = f.getParent() + "/split";
            File d = new File(splitDir);
            if (!d.exists()) System.out.println("d.mkdir() = " + d.mkdir());
            InputStream is = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(is, charset);
            BufferedReader br = new BufferedReader(isr);
            String s = br.readLine();
            long i = 0;
            BufferedWriter bw = null;
            while (s != null) {
                if (i % 10000L == 0) {
                    File dF = new File(splitDir + "/" + i / 10000L);
                    OutputStream os = new FileOutputStream(dF);
                    OutputStreamWriter osw = new OutputStreamWriter(os, charset);
                    bw = new BufferedWriter(osw);
                    System.out.println(i / 10000L);
                }
                bw.write(s);
                bw.newLine();
                bw.flush();
                s = br.readLine();
                ++i;
            }
            if (bw != null) {
                bw.close();
            }
            br.close();
            isr.close();
            is.close();
        }
    }
}
