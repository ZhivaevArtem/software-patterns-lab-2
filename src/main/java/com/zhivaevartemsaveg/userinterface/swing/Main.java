package com.zhivaevartemsaveg.userinterface.swing;

import javax.swing.JFrame;

public class Main extends JFrame {

    public static void main(String[] args) {
        SwingUserInterface swing = new SwingUserInterface("Swing");
        swing.start(1280, 720);
    }
}
