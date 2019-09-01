package com.graphics;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private final Canvas canvas;

    private Main(){
        canvas = new Canvas();

        canvas.setBounds(0, 0, getWidth(), getHeight());
        add(canvas);
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.setSize(800, 600);
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
        main.setTitle("3D Graphics");
    }
}
