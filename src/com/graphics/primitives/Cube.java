package com.graphics.primitives;

import com.graphics.events.MouseData;
import com.graphics.tools.Mesh;
import com.graphics.tools.Triangle;
import com.graphics.ui.Object;
import com.graphics.utils.Axis;
import com.graphics.utils.Tools;
import com.graphics.utils.TransformationMatrices;

import java.awt.*;
import java.util.ArrayList;

public class Cube extends Object {

    private Mesh m;
    private float yRotate = 40, xRotate = 0;

    @Override
    public void initialize() {
        this.m = new Mesh(
                // south
                new Triangle(0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f),
                new Triangle(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f),
                // east
                new Triangle(1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f),
                new Triangle(1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f),
                // north
                new Triangle(1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f),
                new Triangle(1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f),
                // west
                new Triangle(0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f),
                new Triangle(0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f),
                // top
                new Triangle(0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f),
                new Triangle(0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f),
                // bottom
                new Triangle(1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f),
                new Triangle(1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f)

        );
        this.addMesh(m);
    }

    @Override
    public void draw(Graphics2D g, int screenWidth, int screenHeight, int zoomLevel) {
        super.draw(g, screenWidth, screenHeight, zoomLevel);

        m.clearTransformation();
        m.addTransformation(TransformationMatrices.rotate(yRotate, Axis.Y));
        m.addTransformation(TransformationMatrices.rotate(xRotate, Axis.X));
        m.addTransformation(TransformationMatrices.scale(screenWidth / 2f, Axis.X));
        m.addTransformation(TransformationMatrices.scale(screenHeight / 2f, Axis.Y));
        m.addTransformation(TransformationMatrices.scale(2f, Axis.Z));
        m.setTranslateZ(5f);
        yRotate += 1;
        xRotate += 1.2f;
    }

    @Override
    public void onMouseEvent(MouseData data) {
        yRotate -= data.diffX;
        xRotate += data.diffY;
    }
}
