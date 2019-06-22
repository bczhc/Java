package s;/*
package s;*/
/*
package s;*//*

 */
/*
package s;*//*
 */
/*

 *//*

 */
/*
package s;


import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class a implements Runnable {
    private Thread t;
    private int i;
    private static int[] d;
    private static CountDownLatch latch;

    public static void main(String[] args) {
//
        int i_i = 200000;
        d = new int[2 * i_i];
        latch = new CountDownLatch(i_i);
        long sT = System.currentTimeMillis();
        for (int i = 0; i < 2 * i_i; i += 2) {
            new a(i).TStart();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long eT = System.currentTimeMillis();
        System.out.println(Arrays.toString(d));
        System.out.println("d.length = " + d.length);
        System.out.println("Time-taken: " + (eT - sT));
    }

    @Override
    public void run() {
        try {
            d[this.i] = com.zhc.u.Random.ran_sc(0, 10000);
            d[this.i + 1] = com.zhc.u.Random.ran_sc(0, 10000);
            Thread.sleep(50);
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void TStart() {
        if (this.t == null) {
            this.t = new Thread(this);
            this.t.start();
        }
    }

    private a(int a) {
        this.i = a;
    }
}*//*
 */
/*
 *//*

 */
/*



 *//*
 */
/*

 *//*

 */
/*
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class a implements Runnable {
    private int i;
    private Thread t;
    private static CountDownLatch latch;
    private static int[] ints;
    private static int b;

    public static void main(String[] args) {
        int i_i = 32;
        int TCC = 4 * Runtime.getRuntime().availableProcessors();
        b = 10000 / TCC;
        latch = new CountDownLatch(TCC - 1);
        ints = new int[i_i];
        for (int i = 0; i < TCC; i += b) {
            new a(i).S();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(ints));
    }

    private void S() {
        if (t == null) {
            t = new Thread(this);
            t.run();
        }
    }

    @Override
    public void run() {
        try {
            for (int j = 0; j < b; j++) {
                try {
                    ints[i + j] = com.zhc.u.Random.ran_sc(0, 10000);
                } catch (Exception ignored) {}
            }
            Thread.sleep(50);
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private a(int a) {
        i = a;
    }
}*//*
 */
/*
 *//*

 */
/*



import com.zhc.u.Random;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class a implements Runnable {
    private static int[][] ints;
    private Thread t;
    private static CountDownLatch latch;

    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        int c = processors + processors / 2;
        latch = new CountDownLatch(c);
        ints = new int[c][1000000];
        long sT = System.currentTimeMillis();
        for (int i = 0; i < c; i++) {
            new a().S();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long eT = System.currentTimeMillis();
        System.out.println("Over!\n" +
                "Time-taken: " +
                (eT - sT) + "ms");
        new Scanner(System.in).next();
        for (int[] is : ints) {
            System.out.println(Arrays.toString(is));
        }
    }

    @Override
    public void run() {
        int i = Integer.parseInt(Thread.currentThread().getName().replace("Thread-", ""));
        long TsT = System.currentTimeMillis();
        int[] ss = Random.ran(1, 20000, 1000000, true);
        ints[i] = ss;
        long TeT = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " time-taken: " + (TeT - TsT));
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }

    private void S() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    private a() {
    }
}*//*
 */
/*



import com.zhc.u.common.StrDic;

public class a {
    public static void main(String[] args) {
        System.out.println();
        a2.foo(new a3() {
            @Override
            public void j(int i) {
                System.out.println(i);
            }
        });
    }
}

class a2 {
    public static void foo(a3 target) {
        target.j(1);
        target.j(2);
        {
            new com.zhc.u.common.StrDic().d(s -> {

            });
        }
    }
}

abstract class a3 {
    public abstract void j(int i);
}*//*


import com.zhc.u.common.StrDic;
import com.zhc.u.common.StrDicDo;

public class a {
    private int i = 0;

    public static void main(String[] args) {
        a o = new a();

    }
}*/


import java.io.*;
import java.net.URL;

/*
public class a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(
                String.format("http://218.4.55.34/index.aspx?__LASTFOCUS=&__VIEWSTATE=%%2FwEPDwUKMTgxNjY0ODgxMQ9kFgICAw9kFg4CBQ8PFgIeBFRleHQFZ%%2BaCqOS4iuasoeS4jeaYr%%2BS7jui%%2FmeWPsOeUteiEkeeZu%%2BW9leWtpuS5oOW5s%%2BWPsD88YnIvPumaj%%2BaEj%%2BaNouacuuS8mue7meS7luS6uuW4puadpeS4jeS%%2Bv%%2B%%2B8jOivt%%2BeQhuinoyFkZAIHDw9kFgIeB29uQ2xpY2sFFHJldHVybiBkb3VibGVDaGVjaygpZAITDw8WAh8ABRLjgI7kuKrkurrmqKHlvI%%2FjgI9kZAIXDw8WAh8ABQw0OS43NS4yMDkuNTJkZAIZDw8WAh8ABQPlkKZkZAIbDw8WAh8ABQEyZGQCHQ8PFgIfAAU9SVDvvJow5q%%2Br56eSJm5ic3A7Jm5ic3A7Jm5ic3A7Jm5ic3A75Li75py65ZCN77yaMOavq%%2BenkiZuYnNwO2RkZEzfGG57rb%%2BJaEQKS%%2BWfqpekNCHm&__VIEWSTATEGENERATOR=90059987&__EVENTTARGET=&__EVENTARGUMENT=&__EVENTVALIDATION=%%2FwEWBAKb%%2Bd%%2FDDwKYpfGYCALw08i7DQLi44fLCdul7okmMrM%%2F5CG85iujnVfcUcvL&TextBoxuser=%s&TextBoxpwd=%s&Btnlogin=%%E7%%99%%BB%%E5%%BD%%95"
                        , "201811004", "1234575")
        ).openStream()
                , StandardCharsets.UTF_8));
        String r = br.readLine();
        while (true) {
            if (r != null) {
                System.out.println(r);
                r = br.readLine();
            } else break;
        }
    }
}*/


public class a {
    private static InputStream is = null;
    public static void main(String[] args) throws FileNotFoundException {
        /*FileOutputStream os = new FileOutputStream("J:\\G-f.txt");
        FileOutputStream os2 = new FileOutputStream("J:\\G-d.txt");
        OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
        OutputStreamWriter osw2 = new OutputStreamWriter(os2, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        BufferedWriter bw2 = new BufferedWriter(osw2);

        new u_File.TraversalFile("G:\\").Do(new u_File.TraversalFileDo() {
            @Override
            public void f(File f) {
                try {
                    bw.write(f.toString().substring(2) + "\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void d(File d) {
                try {
                    bw2.write(d.toString().substring(2) + "\r\n");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void a(File a) {

            }
        });
        try {
            bw.flush();
            bw2.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            final URL u = new URL("http://www.baidu.com");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream inputStream = u.openStream();
                        System.out.println("is.available() = " + inputStream.available());
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}