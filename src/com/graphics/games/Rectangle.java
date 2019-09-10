package com.graphics.games;

import com.graphics.engine.Renderer;
import com.graphics.engine.Window;

public class Rectangle extends Renderer {
    public Rectangle() {
        super(new float[]{
                // Left bottom triangle
                -0.5f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                // Right top triangle
                0.5f, -0.5f, 0.0f,
                0.5f, 0.5f, 0.0f,
                -0.5f, 0.5f, 0.0f,
        });
    }

    @Override
    public void input(Window window) {

    }

    @Override
    public void update(float interval) {
    }
}
