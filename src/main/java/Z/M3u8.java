package Z;

import java.io.*;
import java.net.URL;

public class M3u8 {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://m3u8.huke88.com/video/hls/v_1/2018-11-19/8BE4FE4C-0C84-6E84-A1B8-6D82FB922CDF.m3u8?pm3u8/0/deadline/1556730881&e=1556720081&token=HUwgVvJnrW6fXOzqd_myfnE3FFoFLWJnNktg7ThD:HkX2e6tGUbWSlw5idv_YaOnFh8U=");
        InputStream is = url.openConnection().getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String r;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = 0;
        while ((r = br.readLine()) != null) {
            if (!r.matches("^#.*") && !r.equals("")) {
                URL u = new URL("http://m3u8.huke88.com" + r);
                System.out.println("com.zhc.u.toString() = " + u.toString());
                try {
                    com.zhc.u.u_File.StreamWrite(u.openStream(), baos);
                    File o1 = new File("C:\\Users\\zhc\\Downloads\\m3u8-ts\\" + i);
                    FileOutputStream fos = new FileOutputStream(o1, false);
                    com.zhc.u.u_File.StreamWrite(u.openStream(), fos);
                    i++;
                    System.out.println("baos.size() = " + baos.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        File o = new File("C:\\Users\\zhc\\Downloads\\m3u8D_Au");
        OutputStream os = new FileOutputStream(o, false);
        com.zhc.u.u_File.StreamWrite(com.zhc.u.u_File.StreamParse(baos), os);
    }
}
