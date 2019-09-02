package com.graphics.ui;

import com.graphics.primitives.Cube;
import com.graphics.tools.Camera;
import com.graphics.tools.LightSource;

public class Workspace {

    private final Canvas canvas;
    private final Cube cube;
    private final Camera camera;
    private final LightSource lightSource;

    public Workspace(Canvas canvas) {
        this.canvas = canvas;
        camera = new Camera(0, 0, 0);
        lightSource = new LightSource(0, 0, -40f);
        cube = new Cube();


        canvas.setCamera(camera);
        canvas.setLightSource(lightSource);

    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void draw() {
        canvas.addObject(cube);
    }
}
