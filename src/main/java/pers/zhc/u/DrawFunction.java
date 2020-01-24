package pers.zhc.u;

import javax.swing.*;
import java.awt.*;

public class DrawFunction extends JFrame {
    private static int W = 1200, H = 900;
    private FourierSeries fs;
    private int nNum;
    private int x0, y0;
    private Graphics G;

    private DrawFunction() {
        init();
    }

    public static void main(String[] args) {
        DrawFunction frame = new DrawFunction();
        frame.setTitle("DrawFunction");
        frame.setSize(W, H);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private double F(double x) {
//        return Math.sin(x) / Math.pow(1.1, -x);//函数表达式
        /*double sum = 0;
        double[] r = new double[15];
        CountDownLatch latch = new CountDownLatch(15);
        for (int n = 1; n < 16; n++) {
            int finalN = n;
            new Thread(() -> {
                r[finalN - 1] = fs.F(n, x);
            })
        }*/
        return fs.F(nNum, x);
    }

    private void setOrigin(int x, int y) {
        this.x0 = x;
        this.y0 = y;
        // show coordinate axis
        drawLine(-W / 2, 0, W / 2, 0);
        drawLine(0, -H / 2, 0, H / 2);
        drawString("X", W / 2 - 30, -20);
        drawString("Y", -20, H / 2 - 20);
        for (int i = 1; i <= 10; i++) {
            draw(W / 2 - i - 6, i);
            draw(W / 2 - i - 6, -i);
        }
        for (int i = 1; i <= 10; i++) {
            draw(-i, H / 2 - i);
            draw(i, H / 2 - i);
        }
    }

    private void init() {
        add(new NewPanel());
        fs = new FourierSeries(20) {
            @Override
            public double f_f(double x) {
                /*if (x < 10) return x;
                if (x >= 10 && x < 20) return -x + 20;
                return x - 20;*/
                /*if (x <= 5) return 0;
                return 1;*/
//                return 10 * Math.sin(x / 10);
//                return -x * x;
                /*if (x < 3) return Math.sqrt(x);
                if (x >= 3 && x < 5) return -x + 15;
                if (x >= 5 && x < 6.5) return x - 15;
                if (x >= 6.5 && x < 8) return 5;
                return 10;*/
                /*if (x < 10) return 20;
                if (x >= 10 && x < 15) return -2 * x + 40;
                if (x >= 15 && x < 25) return 2 * x - 5;
                return -2 * x + 70;*/
                /*if (x < 10) return 3;
                return -3;*/
                if (x < 5) return 10;
                if (x >= 5 && x < 7.5F) return -2 * x + 20;
                if (x >= 7.5F && x < 17.5F) return x - 2.5;
                return -2 * x + 50;
            }
        };
        fs.definite.n = 10000;
        nNum = 10000;
        long t1 = System.currentTimeMillis();
        fs.initAB(nNum, System.out::println, 3);
        long t2 = System.currentTimeMillis();
        System.out.println((t2 - t1) + "ms");
    }

    private int work(int x) {
        //timesx = 0.01;
        //timesy = 100;
        double timesx = 10;
        double timesy = 10;
        return (int) (F(x / timesx) * timesy);
    }

    private void draw(int x, int y) {
        int X = new Coordinate2D(x, y).getPixelPointX();
        int Y = new Coordinate2D(x, y).getPixelPointY();
        G.drawLine(X, Y, X, Y);
    }

    private void drawRec(int x1, int y1, int x2, int y2) {
        int dx = x1 < x2 ? 1 : -1;
        int dy = y1 < y2 ? 1 : -1;
        for (int i = x1; i != x2 + dx; i += dx) {
            for (int j = y1; j != y2 + dy; j += dy) {
                draw(i, j);
            }
        }
    }

    private void drawLine(int x1, int y1, int x2, int y2) {
        int dx = x1 < x2 ? 1 : -1;
        if (x1 == x2) drawRec(x1, y1, x2, y2);
        else {
            double d = (double) (y2 - y1) / (x2 - x1);
            for (int i = x1; i != x2 + dx; i += dx) {
                draw(i, (int) (y1 + (i - x1) * d));
            }
        }
    }

    private void drawString(String s, int x, int y) {
        int X = new Coordinate2D(x, y).getPixelPointX();
        int Y = new Coordinate2D(x, y).getPixelPointY();
        G.drawString(s, X, Y);
    }

    public class Coordinate2D {
        int x, y;

        Coordinate2D(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getPixelPointX() {
            return x0 + x;
        }

        int getPixelPointY() {
            return y0 - y;
        }
    }

    class NewPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            G = g;
            setOrigin(W / 2, H / 2);
            // in the following , draw what you want draw!
            for (int i = -W / 2; i <= W / 2; i++) {
                draw(i, work(i));
//                System.out.println((1 - ((-(double) i - W / 2) / (((double) W))) * 100) + "%");
            }
            /*
            for (int i = 0; i < 1000; i ++) {
                int x = (int)(Math.random() * 400 - 200);
                int y = (int)(Math.random() * 400 - 200);
                drawString("哈哈", x, y);
            }
            */
        }
    }
}