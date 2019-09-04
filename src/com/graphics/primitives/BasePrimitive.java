package com.graphics.primitives;

import com.graphics.events.MouseData;
import com.graphics.engine.Object;
import com.graphics.utils.Axis;
import com.graphics.utils.TransformationMatrices;

public class BasePrimitive extends Object {

    private float yRotate = 40, xRotate = 0;

    public BasePrimitive() {

    }

    public BasePrimitive(String fileName) {
        super(fileName);
    }

    @Override
    public void draw(DrawingParams drawingParams) {
        clearTransformation();
        addTransformation(TransformationMatrices.rotate(yRotate, Axis.Y));
        addTransformation(TransformationMatrices.rotate(xRotate, Axis.X));
        setTranslateZ(2f);
        addTransformation(TransformationMatrices.scale(drawingParams.screenWidth / 2f, Axis.X));
        addTransformation(TransformationMatrices.scale(drawingParams.screenHeight / 2f, Axis.Y));
        super.draw(drawingParams);
        xRotate += 0.1f;
        yRotate += 0.15f;
    }

    @Override
    public void onMouseEvent(MouseData data) {
        yRotate += data.diffX;
        xRotate -= data.diffY;
    }
}
