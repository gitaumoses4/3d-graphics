package com.graphics.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Canvas extends JComponent {

    private final ArrayList<Object> objects;

    public Canvas() {
        objects = new ArrayList<>();
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

        for (Object object : objects) {
            object.draw(g, getWidth(), getHeight());
        }
    }
}
