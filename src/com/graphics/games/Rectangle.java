package com.graphics.games;

import com.graphics.engine.Renderer;
import com.graphics.engine.Window;

public class Rectangle extends Renderer {
    public Rectangle() {
        super(
                new float[]{-0.5f, 0.5f, 0, -0.5f, -0.5f, 0, 0.5f, -0.5f, 0, 0.5f, 0.5f, 0},
                new int[]{0, 1, 3, 3, 1, 2}
        );
    }

    @Override
    public void input(Window window) {

    }

    @Override
    public void update(float interval) {
    }
}
