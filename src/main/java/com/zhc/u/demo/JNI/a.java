package com.zhc.u.demo.JNI;/*
package com.zhc.u.com.zhc.u.com.zhc.u.com.zhc.u.com.zhc.u.demo.JNI;


import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.*;

public class a {
    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        String s = new Scanner(in).next();
        CountDownLatch latch = new CountDownLatch(100);
        final URL com.zhc.u = new URL(s);
//        URL com.zhc.u = new URL("");
        final CountDownLatch finalLatch = latch;
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] b = new byte[16];
                    out.println(com.zhc.u.openConnection().getInputStream().read(b));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finalLatch.countDown();
            }
        };
        for (int j = 0; j < 100; j++) {
            ExecutorService es = Executors.newFixedThreadPool(100);
            for (int i = 0; i < 100; i++) {
                es.execute(r1);
            }
            latch.await();
            es.shutdown();
            latch = new CountDownLatch(100);
        }
    }

    private native String f1(String s, int i);

    private native byte[] f2(String s);

    private native String f3(byte[] bytes);

    private native String f4(byte b);

    private native void cls();
}

class b {
    public static void main(String[] args) {
        BufferedImage bi = new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);
        out.println(bi.getHeight());
        JFrame jf = new JFrame();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setBounds(100, 100, 100, 100);
    }
}

class c {
    public static void main(String[] args) {
        loadLibrary("b");
//        load("C:\\tomcat_Server\\web\\WEB-INF\\lib\\b.dll");
        c o = new c();
        out.println("o.f1(2) = " + o.f1(2));
    }

    private native int f1(int i);

    private native String f2(String i);
}*/


import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

class a {
    //    @SuppressWarnings("InfiniteRecursion")
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

// 用ODBC连接数据源的方式
        Connection con;

        Statement sql;

        ResultSet rs;
        Properties p = new Properties();
        p.put("charSet", "GBK");
        con = DriverManager.getConnection("jdbc:odbc:demo1", p);

// 用java代码直接连接access文件

// con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=shop.mdb","","");

        sql = con.createStatement();

        rs = sql.executeQuery("SELECT * FROM f1");
        List<String> un = new ArrayList<>();
        List<String> pw = new ArrayList<>();
        while (rs.next()) {
            un.add(rs.getString("username"));
            pw.add(rs.getString("password"));
        }
        System.out.println("Arrays.toString(un.toArray(new String[0])) = " + Arrays.toString(un.toArray(new String[0])));
        System.out.println("Arrays.toString(pw.toArray(new String[0])) = " + Arrays.toString(pw.toArray(new String[0])));
    }
//    private native int f1(int i);
}
