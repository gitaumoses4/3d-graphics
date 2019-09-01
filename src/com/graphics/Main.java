package com.graphics;

import com.graphics.ui.Canvas;
import com.graphics.ui.Workspace;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JFrame {

    private final Canvas canvas;
    private final Workspace workspace;
    private Timer timer;
    private static final int FRAME_RATE = 20;

    private Main() {
        canvas = new Canvas();
        workspace = new Workspace(canvas);

        canvas.setBounds(0, 0, getWidth(), getHeight());
        add(canvas);

        timer = new Timer();
    }

    public void startTimer() {
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
        main.startTimer();
    }
}
