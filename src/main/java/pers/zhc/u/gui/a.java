package pers.zhc.u.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class a {
    private static boolean c = false;

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final double[] n = new double[10];
        n[0] = 0;
        n[1] = 0;
        JPanel jp = new JPanel() {
            @Override
            public void paint(Graphics graphics) {
//                super.paint(graphics);
                if (c) {
                    graphics.clearRect(0, 0, 2000, 2000);
                    c = false;
                }
                graphics.setColor(Color.red);
                graphics.drawLine(0, 1000, 1000, 1000);
                graphics.drawLine(1000, 0, 1000, 1000);
                graphics.drawLine(0, 0, 0, 1000);
                graphics.drawLine(0, 0, 1000, 0);
                graphics.setColor(Color.BLACK);
                int x = ((int) n[0]) + 500;
                int y = ((int) n[1]) + 500;
                graphics.fillArc(x, y, 5, 5, 0, (int) (360 * Math.PI));
                System.out.println("xy: " + x + "\t" + y);
            }
        };
        jf.add(jp);
        jp.repaint();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                jp.repaint();
                n[0] = (Math.sin(n[3])) * 500;
                n[1] = (Math.sin(n[4])) * 500;
                n[3] += n[5];
                n[4] += n[6];
            }
        }, 0, 1);
        jf.setSize(1100, 1100);
        jf.setVisible(true);
        JFrame jf2 = new JFrame("2");
        JTextField jtf = new JTextField(40);
        jf2.add(jtf);
        jf2.setVisible(true);
        jtf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    try {
                        String[] split = jtf.getText().split(",");
                        n[5] = Double.parseDouble(split[0]);
                        n[6] = Double.parseDouble(split[1]);
                        c = true;
                    } catch (Exception ignored) {
                        n[5] = 0;
                        n[6] = 0;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        jf2.setBounds(1000, 300, 1300, 400);
    }
}