package com.graphics.games;

import com.graphics.engine.Renderer;
import com.graphics.engine.StaticShader;
import com.graphics.engine.Window;

public class Rectangle extends Renderer {
    private StaticShader staticShader;

    public Rectangle() {
        super(
                new float[]{-0.5f, 0.5f, 0, -0.5f, -0.5f, 0, 0.5f, -0.5f, 0, 0.5f, 0.5f, 0},
                new int[]{0, 1, 3, 3, 1, 2},
                new float[]{
                        0, 0,
                        0, 1,
                        1, 1,
                        1, 0
                }
        );
    }

    @Override
    public void init() throws Exception {
        super.init();
        staticShader = new StaticShader();
        staticShader.start();
    }

    @Override
    public void input(Window window) {

    }

    @Override
    public void update(float interval) {
    }

    @Override
    public void finish() {
        super.finish();
        staticShader.stop();
    }
}
