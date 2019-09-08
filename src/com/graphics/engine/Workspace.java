package com.graphics.engine;

import com.graphics.primitives.Cube;
import com.graphics.primitives.Sphere;
import com.graphics.tools.Camera;
import com.graphics.tools.LightSource;
import com.graphics.tools.Paint;

public class Workspace {

    private final DummyGame dummyGame;
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
        this.dummyGame = new DummyGame(drawingParams);
    }

    public DummyGame getDummyGame() {
        return dummyGame;
    }

    public void draw() {
//        dummyGame.addObject(cube);
        dummyGame.addObject(sphere);
    }
}
