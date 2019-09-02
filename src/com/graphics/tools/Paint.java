package com.graphics.tools;

import java.awt.Graphics2D;

public interface Paint {
    void draw(Graphics2D g, int screenWidth, int screenHeight, int zoomLevel);
}
