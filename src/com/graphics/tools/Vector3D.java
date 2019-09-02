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

    public Vector3D scale(float value) {
        return new Vector3D(toMatrix().multiply(value));
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

    public Vector3D subtract(Vector3D another) {
        return new Vector3D(
                this.x - another.x,
                this.y - another.y,
                this.z - another.z
        );
    }

    public Vector3D add(Vector3D another) {
        return new Vector3D(
                this.x + another.x,
                this.y + another.y,
                this.z + another.z
        );
    }

    public Vector3D cross(Vector3D another) {
        return new Vector3D(
                this.y * another.z - this.z * another.y,
                this.z * another.z - this.x * another.z,
                this.x * another.y - this.y * another.x
        );
    }

    public float magnitude() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public float dot(Vector3D another) {
        return x * another.x + y * another.y + z * another.z;
    }

    @Override
    public String toString() {
        return String.format("[%.2f, %.2f, %.2f]", x, y, z);
    }
}
