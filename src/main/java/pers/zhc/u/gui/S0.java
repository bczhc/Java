package pers.zhc.u.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class S0 extends JPanel {
    int x = 50;
    int y = 50;

    public static void main(String[] ars) {
        JFrame frame = new JFrame("paintTest");
        S0 panel = new S0();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
//        panel.repaint();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (panel.x <= 300) {
                    panel.x++;
                    panel.repaint();
                }
            }
        }, 0, 20);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.red);
        g.fillOval(x, y, 30, 30);
    }
}