package com.wubin.test.z;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ImageFrame {

    public static void main(String[] args) throws AWTException {
//        JPanel jPanel = new JPanel();
//        jPanel.setLayout(new GridLayout(3, 1, 5, 5));
//        jPanel.add(new JButton("1"));
//        jPanel.add(new JButton("2"));
//        jPanel.add(new JButton("3"));
//
//        JFrame jFrame = new JFrame("HelloWorld!!!");
//        jFrame.setBounds(300, 100, 400, 200);
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jFrame.add(jPanel);
//        jFrame.setVisible(true);

        JFrame frame = new JFrame("鼠标连点器");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Robot robot = new Robot();
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // 当按下并释放键时调用
//                System.out.println("Key typed: " + KeyEvent.getKeyText(e.getKeyCode()));
                System.out.println("start");
                for (int i = 0; i < 200; ++i) {
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                System.out.println("end");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // 当按下键时调用
//                System.out.println("Key pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // 当释放键时调用
//                System.out.println("Key released: " + KeyEvent.getKeyText(e.getKeyCode()));
            }
        });
    }

}
