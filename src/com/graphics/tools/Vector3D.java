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

    public Vector3D transform(Matrix matrix) {
        Matrix vectorMatrix;
        if (matrix.getRows() == 4) {
            vectorMatrix = toMatrix(true, 1);
        } else {
            vectorMatrix = toMatrix();
        }
        vectorMatrix = vectorMatrix.multiply(matrix);
        return new Vector3D(vectorMatrix);
    }

    public Matrix toMatrix() {
        return this.toMatrix(false, 0);
    }

    public Matrix toMatrix(boolean addColumn, float value) {
        if (addColumn) {
            return new Matrix(1, 4, x, y, z, value);
        }
        return new Matrix(1, 3, x, y, z);
    }


    public Vector3D getProjected(int screenWidth, int screenHeight) {
        return TransformationMatrices.applyProjection(screenWidth, screenHeight, this);
    }

    @Override
    public String toString() {
        return String.format("[%.2f, %.2f, %.2f]", x, y, z);
    }
}
