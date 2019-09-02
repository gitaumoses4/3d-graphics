package com.graphics.utils;

import com.graphics.tools.Paint;
import com.graphics.tools.Vector3D;


public class TransformationMatrices {
    public static Matrix projection(Paint.DrawingParams drawingParams) {
        float fNear = 0.1f;
        float fFar = 10000.0f;
        float fFov = 90.0f / (drawingParams.zoomLevel + 1);
        float fAspectRatio = (float) drawingParams.screenHeight / (float) drawingParams.screenWidth;
        float fFovRad = 1.0f / (float) Math.tan(Math.toRadians(fFov * 0.5f));

        return new Matrix(4, 4)
                .set(0, 0, fAspectRatio * fFovRad)
                .set(1, 1, fFovRad)
                .set(2, 2, fFar / (fFar - fNear))
                .set(3, 2, (-fFar * fNear) / (fFar - fNear))
                .set(2, 3, 1.0f);
    }

    public static Matrix scale(float value, Axis axis) {
        Matrix m = Matrix.transformation();
        switch (axis) {
            case X:
                m.set(0, 0, value);
                break;
            case Y:
                m.set(1, 1, value);
                break;
            case Z:
                m.set(2, 2, value);
                break;
        }
        return m;
    }

    public static Matrix rotate(float angle, Axis axis) {
        Matrix m = Matrix.transformation();
        float cos = (float) Math.cos(Math.toRadians(angle));
        float sin = (float) Math.sin(Math.toRadians(angle));
        switch (axis) {
            case X:
                m.set(1, 1, cos);
                m.set(1, 2, -sin);
                m.set(2, 1, sin);
                m.set(2, 2, cos);
                break;
            case Y:
                m.set(0, 0, cos);
                m.set(0, 2, sin);
                m.set(2, 0, -sin);
                m.set(2, 2, cos);
                break;
            case Z:
                m.set(0, 0, cos);
                m.set(0, 1, -sin);
                m.set(1, 0, sin);
                m.set(1, 1, cos);
                break;
        }
        return m;
    }

    ;

    public static Matrix translate(float value, Axis axis) {
        Matrix m = Matrix.identity(4);
        m.set(0, 0, 1);
        m.set(1, 1, 1);
        m.set(2, 2, 1);
        switch (axis) {
            case X:
                m.set(0, 3, value);
                break;
            case Y:
                m.set(1, 3, value);
                break;
            case Z:
                m.set(2, 3, value);
                break;
        }
        return m;
    }


    public static Vector3D applyProjection(Paint.DrawingParams drawingParams, Vector3D vector3D) {
        Matrix vectorMatrix = vector3D.toMatrix(true, 1);
        Matrix result = vectorMatrix.multiply(TransformationMatrices.projection(drawingParams));


        Vector3D vector = new Vector3D(result);
        float w = result.get(0, 3);
        if (w != 0.0f) {
            vector.setX(vector.getX() / w);
            vector.setY(vector.getY() / w);
            vector.setZ(vector.getZ() / w);
        }
        return vector;
    }

    public static Vector3D applyTranslation(float x, float y, float z, Vector3D vector) {
        Matrix translationMatrix = Matrix.identity(4);
        translationMatrix = translationMatrix.multiply(TransformationMatrices.translate(x, Axis.X));
        translationMatrix = translationMatrix.multiply(TransformationMatrices.translate(y, Axis.Y));
        translationMatrix = translationMatrix.multiply(TransformationMatrices.translate(z, Axis.Z));
        Matrix matrix = translationMatrix.multiply(vector.toMatrix(true, 1).transpose());
        return new Vector3D(matrix.transpose());
    }
}
