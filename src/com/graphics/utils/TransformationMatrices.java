package com.graphics.utils;

import com.graphics.tools.Vector3D;

public class TransformationMatrices {
    public static Matrix projection(int screenWidth, int screenHeight) {
        float fNear = 0.1f;
        float fFar = 1000.0f;
        float fFov = 90.0f;
        float fAspectRatio = (float) screenHeight / (float) screenWidth;
        float fFovRad = 1.0f / (float) Math.tan(fFov * 0.5f / 180f * Math.PI);

        return new Matrix(4, 4)
                .set(0, 0, fAspectRatio * fFovRad)
                .set(1, 1, fFovRad)
                .set(2, 2, fFar / (fFar - fNear))
                .set(3, 2, (-fFar * fNear) / (fFar - fNear))
                .set(2, 3, 1.0f);
    }

    public static Vector3D applyProjection(int screenWidth, int screenHeight, Vector3D vector3D) {
        Matrix vectorMatrix = new Matrix(1, 4, vector3D.getX(), vector3D.getY(), vector3D.getZ(), 1);
        Matrix result = vectorMatrix.multiply(TransformationMatrices.projection(screenWidth, screenHeight));


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
