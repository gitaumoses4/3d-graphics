package com.graphics.utils;

import com.graphics.tools.Vector3D;


public class TransformationMatrices {
    public static Matrix projection(int screenWidth, int screenHeight, int zoomLevel) {
        float fNear = 0.1f;
        float fFar = 1000.0f;
        float fFov = 90.0f / (zoomLevel + 1);
        float fAspectRatio = (float) screenHeight / (float) screenWidth;
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


    public static Vector3D applyProjection(int screenWidth, int screenHeight, int zoomLevel, Vector3D vector3D) {
        Matrix vectorMatrix = vector3D.toMatrix(true, 1);
        Matrix result = vectorMatrix.multiply(TransformationMatrices.projection(screenWidth, screenHeight, zoomLevel));


        Vector3D vector = new Vector3D(result.get(0, 0), result.get(0, 1), result.get(0, 2));
        float w = result.get(0, 3);
        if (w != 0.0f) {
            vector.setX(vector.getX() / w);
            vector.setY(vector.getY() / w);
            vector.setZ(vector.getZ() / w);
        }
        return vector;
    }
}
