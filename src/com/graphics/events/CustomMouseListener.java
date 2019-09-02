package com.graphics.events;

import java.awt.event.*;
import java.util.ArrayList;

public class CustomMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {
    private int prevX, prevY, diffX, diffY;

    private ArrayList<MouseInfoListener> listeners = new ArrayList<>();

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    public void addMouseInfoListener(MouseInfoListener mouseInfoListener) {
        this.listeners.add(mouseInfoListener);
    }


    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        this.prevX = mouseEvent.getX();
        this.prevY = mouseEvent.getY();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        diffX = -1;
        diffY = -1;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        diffX = mouseEvent.getX() - prevX;
        diffY = mouseEvent.getY() - prevY;
        MouseData mouseData = new MouseData();
        mouseData.diffX = diffX;
        mouseData.diffY = diffY;
        mouseData.type = MouseData.Type.DRAG;
        fireEvent(mouseData);
        prevX = mouseEvent.getX();
        prevY = mouseEvent.getY();
    }

    private void fireEvent(MouseData mouseData) {
        for (MouseInfoListener mouseInfoListener : listeners) {
            mouseInfoListener.onMouseEvent(mouseData);
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        int value = mouseWheelEvent.getUnitsToScroll();
        MouseData mouseData = new MouseData();
        mouseData.zoom = value;
        mouseData.type = value < 0 ? MouseData.Type.ZOOM_IN : MouseData.Type.ZOOM_OUT;
        fireEvent(mouseData);
    }
}
