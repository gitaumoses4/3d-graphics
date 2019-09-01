package com.graphics.tools;

import com.graphics.utils.Matrix;
import com.graphics.utils.TransformationMatrices;

public class Vector3D {
    private float x, y, z;

    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(Matrix matrix) {
        this.x = matrix.get(0, 0);
        this.y = matrix.get(0, 1);
        this.z = matrix.get(0, 2);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Matrix toMatrix() {
        return new Matrix(1, 3, x, y, z);
    }

    public Vector3D scale(float value) {
        return new Vector3D(x * value, y * value, z * value);
    }

    public Vector3D getProjected(int screenWidth, int screenHeight) {
        return TransformationMatrices.applyProjection(screenWidth, screenHeight, this);
    }

}
