package com.wubin.test.z;

import javax.swing.*;
import java.awt.*;

public class ImageFrame {

    public static void main(String[] args) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3, 1, 5, 5));
        jPanel.add(new JButton("1"));
        jPanel.add(new JButton("2"));
        jPanel.add(new JButton("3"));

        JFrame jFrame = new JFrame("HelloWorld!!!");
        jFrame.setBounds(300, 100, 400, 200);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }

}
