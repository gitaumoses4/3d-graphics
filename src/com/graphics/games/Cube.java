package com.graphics.games;

import com.graphics.engine.models.Model;

public class Cube extends Model {

    @Override
    public float[] getPositionCoordinates() {
        return new float[]{
                -0.5f, +0.5f, +0.5f, -0.5f, -0.5f, +0.5f, +0.5f, -0.5f, +0.5f, +0.5f, +0.5f, +0.5f,
                -0.5f, +0.5f, -0.5f, -0.5f, -0.5f, -0.5f, +0.5f, -0.5f, -0.5f, +0.5f, +0.5f, -0.5f,
        };
    }

    @Override
    public int[] getIndices() {
        return new int[]{
                0, 1, 3, 3, 1, 2, 0, 4, 5, 5, 1, 0, 1, 5, 2, 2, 5, 6,
                3, 7, 6, 6, 2, 3, 0, 4, 7, 7, 3, 0, 4, 5, 7, 7, 5, 6
        };
    }

    @Override
    public float[] getTexturedCoordinates() {
        return new float[]{
                0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0,
                0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0,
        };
    }
}
