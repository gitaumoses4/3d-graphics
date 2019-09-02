package com.graphics.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;

import com.graphics.events.CustomMouseListener;
import com.graphics.events.MouseData;
import com.graphics.events.MouseInfoListener;
import com.graphics.tools.Camera;
import com.graphics.tools.LightSource;
import com.graphics.tools.Paint;
import com.graphics.utils.Axis;
import com.graphics.utils.TransformationMatrices;

import java.util.TimerTask;

public class Canvas extends JComponent implements MouseInfoListener {

    private final ArrayList<Object> objects;
    private static final int FRAME_RATE = 30;
    private final CustomMouseListener customMouseListener;
    private float zoomLevel;
    private Camera camera;
    private LightSource lightSource;

    public Canvas() {
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
    }

    public void addObject(Object object) {
        this.objects.add(object);
        this.addMouseListener(object);
    }

    public void addMouseListener(MouseInfoListener mouseInfoListener) {
        this.customMouseListener.addMouseInfoListener(mouseInfoListener);
    }


    public void setLightSource(LightSource lightSource) {
        this.lightSource = lightSource;
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

        g.translate(getWidth() / 2, getHeight() / 2);
        for (int i = 0; i < objects.size(); i++) {
            Paint.DrawingParams drawingParams = new Paint.DrawingParams();
            drawingParams.camera = camera;
            drawingParams.g = g;
            drawingParams.screenHeight = getHeight();
            drawingParams.screenWidth = getWidth();
            drawingParams.zoomLevel = zoomLevel;
            drawingParams.lightSource = lightSource;
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

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}
