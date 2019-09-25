package com.graphics.engine.events;

public interface MouseListener {
    void onMousePressed(float x, float y);

    void onMouseDragged(float x, float y, float dx, float dy);

    void onMouseMoved(float x, float y, float dx, float dy);

    void onMouseScroll(float dx, float dy);
}
