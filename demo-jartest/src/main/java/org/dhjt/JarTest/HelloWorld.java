package org.dhjt.JarTest;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelloWorld {
    private JFrame jFrame;
    private JPanel jPanel;
    private JLabel label;
    public HelloWorld() {
        jFrame = new JFrame("HelloWorld Test Frame");
        label = new JLabel("Hello, world!");
        jPanel = new JPanel();
        jPanel.add(label);
        jFrame.add(jPanel);
        jFrame.pack();
        jFrame.setSize(400, 150);
        jFrame.setLocation(400, 300);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    // webstart 启动时执行的主方法
    public static void main(String args[]) {
        new HelloWorld();
    }
}