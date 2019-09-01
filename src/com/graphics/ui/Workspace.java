package com.graphics.ui;

import com.graphics.primitives.Cube;

public class Workspace {

    private final Canvas canvas;
    private final Cube cube;

    public Workspace(Canvas canvas) {
        this.canvas = canvas;
        cube = new Cube();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void draw() {
        canvas.addObject(cube);
    }
}
