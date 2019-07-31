package com.zhc.u.demo;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Date;

public class a implements Runnable {
    private Thread t;
    private String s;

    private a(String a) {
        this.s = a;
    }

    @Override
    public void run() {
        try {
//            JOptionPane.showMessageDialog(null, new Date().toString(), s, JOptionPane.INFORMATION_MESSAGE);
//            JFrame jFrame = new JFrame(new Date().toString());
//            jFrame.setVisible(true);
//            JLabel jLabel = new JLabel("lol");
            /*jFrame.add(jLabel);
            jFrame.setBounds(300, 300, 300, 100);*/
//            jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//            JTextField jTextField = new JTextField();
//            jFrame.add(jTextField);
//            SwingLoginExample.main(new String[0]);
            JFrame jFrame = new JFrame("Demo");
            jFrame.setVisible(true);;
            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jFrame.setBounds(500, 300, 100, 100);
            JPanel jPanel = new JPanel();
            jFrame.add(jPanel);
            JTextField jTextField = new JTextField(20);
            jTextField.setBounds(0, 0, 50, 30);
            jPanel.add(jTextField);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "error", JOptionPane.INFORMATION_MESSAGE);
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void TStart() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    public static void main(String[] args) {
//        JFrame jf = new JFrame("zhc");
//        jf.setVisible(true);
//        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        JLabel jl = new JLabel(Arrays.toString(args));
//        jf.add(jl);
//        jf.setSize(500, 500);
//        JButton jb = new JButton("click");
//        jb.addActionListener(e -> JOptionPane.showMessageDialog(jb, "A"));
//        jf.add(jb);
//        JOptionPane.showMessageDialog(null, "A");
        System.out.println("Arrays.toString(Color.RGBtoHSB(255, 255, 255, null)) = " + Arrays.toString(Color.RGBtoHSB(255, 255, 255, null)));
        JFrame jFrame = new JFrame("zhc");
        JButton jButton = new JButton("click!");
//        jFrame.setContentPane(jButton);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new a(new Date().toString()).TStart();
            }
        });
        jFrame.add(jButton);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
//        jFrame.setSize(100, 100);
        jFrame.setBounds(100, 100, 500, 300);
    }
}