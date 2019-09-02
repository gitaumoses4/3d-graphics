package com.graphics.tools;

import com.graphics.utils.Matrix;

public class Camera extends Vector3D {
    public Camera(float x, float y, float z) {
        super(x, y, z);
    }

    public Camera(Matrix matrix) {
        super(matrix);
    }
}
