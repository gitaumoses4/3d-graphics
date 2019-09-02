package com.graphics.tools;

import com.graphics.utils.Matrix;

public class LightSource extends Vector3D {
    public LightSource(float x, float y, float z) {
        super(x, y, z);
    }

    public LightSource(Matrix matrix) {
        super(matrix);
    }
}
