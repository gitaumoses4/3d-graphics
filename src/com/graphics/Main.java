package com.graphics;

import com.graphics.ui.Canvas;
import com.graphics.ui.Workspace;

import javax.swing.*;

public class Main extends JFrame {

    private final Canvas canvas;
    private final Workspace workspace;

    private Main() {
        canvas = new Canvas();
        workspace = new Workspace(canvas);

        canvas.setBounds(0, 0, getWidth(), getHeight());
        add(canvas);
    }

    public void draw() {
        workspace.draw();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setSize(1000, 800);
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
        main.setTitle("3D Graphics");
        main.draw();
    }
}
