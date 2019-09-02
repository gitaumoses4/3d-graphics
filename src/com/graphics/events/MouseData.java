package com.graphics.events;

public class MouseData {
    public enum Type {
        DRAG,
        ZOOM_IN,
        ZOOM_OUT
    }

    public int diffX;
    public int diffY;
    public int zoom;
    public Type type;
}
