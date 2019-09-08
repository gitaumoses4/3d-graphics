package com.graphics.engine;

import java.util.ArrayList;

import com.graphics.events.CustomMouseListener;
import com.graphics.events.MouseData;
import com.graphics.events.MouseInfoListener;
import com.graphics.tools.Paint;

import static org.lwjgl.opengl.GL11.*;


public class DummyGame implements MouseInfoListener, GameLogic {

    private final ArrayList<Object> objects;
    private final CustomMouseListener customMouseListener;
    private final Paint.DrawingParams drawingParams;
    private float zoomLevel;

    public DummyGame(Paint.DrawingParams drawingParams) {
        objects = new ArrayList<>();

        customMouseListener = new CustomMouseListener();
        customMouseListener.addMouseInfoListener(this);

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
    public void onMouseEvent(MouseData data) {
        if (data.type == MouseData.Type.ZOOM_IN || data.type == MouseData.Type.ZOOM_OUT) {
            float value = 0.1f;
            this.zoomLevel += data.type == MouseData.Type.ZOOM_IN ? value : -value;
        } else if (data.type == MouseData.Type.DRAG) {
        }
    }

    @Override
    public void init() throws Exception {

    }

    @Override
    public void input(Window window) {

    }

    @Override
    public void update(float interval) {

    }

    @Override
    public void render(Window window) {
        drawingParams.zoomLevel = zoomLevel;
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

//        for (int i = 0; i < objects.size(); i++) {
//            objects.get(i).draw(drawingParams);
//        }
        glBegin(GL_TRIANGLES);
//        glVertex2f(200, 100);
//        glVertex2f(300, 400);
//        glVertex2f(100, 400);
        glEnd();
    }
}
