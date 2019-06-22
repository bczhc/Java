package com.zhc.u.demo.Thread;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class R0 implements Runnable {
    private Thread t;
    private File f;

    R0(int i) {
        this.f = new File("C:\\zhc\\f\\s2\\" + i);
    }

    @Override
    public void run() {
        try {
            this.f.createNewFile();
            Thread.sleep(50);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }
}

public class a {
    public static void main(String[] args) {
        if (!new Scanner(System.in).nextLine().equals("")) {
            for (int i = 0; i < 100000; i++) {
                new R0(i).start();
            }
        }
    }
}
