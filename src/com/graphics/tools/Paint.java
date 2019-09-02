package com.graphics.tools;

import java.awt.Graphics2D;

public interface Paint {
    void draw(DrawingParams drawingParams);

    public class DrawingParams {
        public Camera camera;
        public Graphics2D g;
        public int screenWidth;
        public int screenHeight;
        public float zoomLevel;
        public LightSource lightSource;
    }
}
