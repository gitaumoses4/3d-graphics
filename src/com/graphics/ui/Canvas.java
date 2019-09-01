package com.graphics.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Canvas extends JComponent {

    private final ArrayList<Object> objects;
    private static final int FRAME_RATE = 30;

    public Canvas() {
        objects = new ArrayList<>();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, 1000 / FRAME_RATE);
    }

    public void addObject(Object object) {
        this.objects.add(object);
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
        g.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        g.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.translate(getWidth()/ 2, getHeight() / 2);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).draw(g, getWidth(), getHeight());
        }
    }
}
