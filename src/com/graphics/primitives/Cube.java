package com.graphics.primitives;

import com.graphics.tools.Mesh;
import com.graphics.tools.Triangle;
import com.graphics.ui.Object;
import com.graphics.utils.Axis;
import com.graphics.utils.Tools;
import com.graphics.utils.TransformationMatrices;

import java.util.ArrayList;

public class Cube extends Object {

    public float yRotate = 0;

    @Override
    public void initialize() {
        Mesh m = new Mesh(
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

        Tools.setInterval(() -> {
            m.clearTransformation();
            m.addTransformation(TransformationMatrices.rotate(yRotate, Axis.Y));
            m.addTransformation(TransformationMatrices.rotate(yRotate, Axis.X));
            m.addTransformation(TransformationMatrices.scale(800, Axis.X));
            m.addTransformation(TransformationMatrices.scale(800, Axis.Y));
            m.addTransformation(TransformationMatrices.scale(2, Axis.Z));
            yRotate += 10;
        }, 100);

    }
}
