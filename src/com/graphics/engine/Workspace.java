package com.graphics.engine;

import com.graphics.primitives.Cube;
import com.graphics.primitives.Sphere;
import com.graphics.tools.Camera;
import com.graphics.tools.LightSource;
import com.graphics.tools.Paint;

public class Workspace {

    private final MCanvas canvas;
    private final Cube cube;
    private final Object sphere;
    private final Camera camera;
    private final Paint.DrawingParams drawingParams;
    private final LightSource lightSource;

    public Workspace() {
        camera = new Camera(0, 0, 0);
        lightSource = new LightSource(0, 0, -40f);
        cube = new Cube();
        sphere = new Sphere();

        drawingParams = new Paint.DrawingParams();
        drawingParams.camera = camera;
        drawingParams.lightSource = lightSource;
        this.canvas = new MCanvas(drawingParams);
    }

    public MCanvas getCanvas() {
        return canvas;
    }

    public void draw() {
//        canvas.addObject(cube);
        canvas.addObject(sphere);
    }
}
