package com.zhc.u;/*
package com.zhc.u;

import javax.swing.*;
import java.awt.*;

public class s implements Runnable {
    private static s o = new s();
    private Thread t;
    private int A = 0;
    private int scW = 0;
    private int scH = 0;

    private s(int i) {
        A = i;
    }

    private s() {
    }

    @Override
    public void run() {
        o.CAS(A, 0);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        o.scW = (int) screenSize.getWidth();
        o.scH = (int) screenSize.getHeight();
        for (int i = 0; i < 3; i++) {
            new s(i).start();
        }
        try {
            if (args.length > 0) {
                o.CAS(-1, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        o.CAS(-1, 1);
    }

    private void CAS(int title, int c) {
        JFrame jFrame = new JFrame(String.valueOf(title));
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel jLabel = new JLabel("hello, world\t" + title);
        o.jFrameSetBounds(jFrame);
        jFrame.getContentPane().add(jLabel);
        jFrame.pack();
        jFrame.setVisible(true);
        if (c == 1) {
//            o.closeJF(jFrame);
//            jFrame.removeAll();
        }
    }

    private void jFrameSetBounds(JFrame jFrame) {
        jFrame.setBounds(com.zhc.u.Random.ran_sc(100, o.scW)
                , com.zhc.u.Random.ran_sc(100, o.scH), 0, 0);
    }

    private void closeJF(JFrame jFrame) {
        jFrame.dispose();
    }
}*/


/*
import java.io.*;
import java.nio.charset.StandardCharsets;

public class s {
    public static void main(String[] args) throws IOException {
        System.out.println("\"aa\\u202Ebb\\u202D\" = " + "aa\u202Ebb\u202D");
        System.out.println("\"\\u2028\" = " + "\u2028");
        OutputStream os = new FileOutputStream("C:\\Users\\zhc\\Downloads\\s.txt");
        OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_16);
        osw.write("A\u2028b");
        osw.flush();
    }
}*/


public class s {
    private boolean b = false;

    private void f1() {
        System.out.println(1);
    }

    public int f2(String s) {
        return Integer.valueOf(s);
    }

    public static void main(String[] args) {
        s s = new s();
        s.f1();
        System.out.println("s.f2(\"a.\") = " + s.f2("a."));
        new s().b = true;
        s.b = true;
    }
}