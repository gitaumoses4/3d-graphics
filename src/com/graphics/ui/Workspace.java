package com.graphics.ui;

import com.graphics.primitives.Cube;
import com.graphics.tools.Camera;

public class Workspace {

    private final Canvas canvas;
    private final Cube cube;
    private final Camera camera;

    public Workspace(Canvas canvas) {
        this.canvas = canvas;
        camera = new Camera(0, 0, 0);
        canvas.setCamera(camera);
        cube = new Cube();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void draw() {
        canvas.addObject(cube);
    }
}
