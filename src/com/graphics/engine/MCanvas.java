package com.graphics.engine;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

import com.graphics.events.CustomMouseListener;
import com.graphics.events.MouseData;
import com.graphics.events.MouseInfoListener;
import com.graphics.tools.Paint;

import javax.swing.*;
import java.util.TimerTask;

public class MCanvas extends JPanel implements MouseInfoListener {

    private final ArrayList<Object> objects;
    private static final int FRAME_RATE = 20;
    private final CustomMouseListener customMouseListener;
    private final Paint.DrawingParams drawingParams;
    private float zoomLevel;
    private final HashMap<RenderingHints.Key, java.lang.Object> renderingHints = new HashMap<>();

    public MCanvas(Paint.DrawingParams drawingParams) {
        objects = new ArrayList<>();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, 1000 / FRAME_RATE);

        customMouseListener = new CustomMouseListener();
        addMouseListener(customMouseListener);
        addMouseMotionListener(customMouseListener);
        addMouseWheelListener(customMouseListener);
        customMouseListener.addMouseInfoListener(this);


        renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

        this.drawingParams = drawingParams;
    }

    public void addObject(Object object) {
        this.objects.add(object);
        this.addMouseListener(object);
    }

    public void addMouseListener(MouseInfoListener mouseInfoListener) {
        this.customMouseListener.addMouseInfoListener(mouseInfoListener);
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    @Override
    public void paint(Graphics gr) {
        super.paint(gr);
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHints(renderingHints);
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        drawingParams.g = g;
        drawingParams.screenHeight = getHeight();
        drawingParams.screenWidth = getWidth();
        drawingParams.zoomLevel = zoomLevel;

        g.translate(getWidth() / 2, getHeight() / 2);

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).draw(drawingParams);
        }
    }

    @Override
    public void onMouseEvent(MouseData data) {
        if (data.type == MouseData.Type.ZOOM_IN || data.type == MouseData.Type.ZOOM_OUT) {
            float value = 0.1f;
            this.zoomLevel += data.type == MouseData.Type.ZOOM_IN ? value : -value;
        }else if(data.type == MouseData.Type.DRAG){
        }
    }
}
