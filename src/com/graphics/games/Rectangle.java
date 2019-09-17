package com.graphics.games;

import com.graphics.engine.models.Model;

public class Rectangle extends Model {

    public Rectangle() {
    }

    @Override
    public float[] getPosition() {
        return new float[]{
                -0.5f, 0.5f, 0,
                -0.5f, -0.5f, 0,
                0.5f, -0.5f, 0,
                0.5f, 0.5f, 0};
    }

    @Override
    public int[] getIndices() {
        return new int[]{0, 1, 3, 3, 1, 2};
    }

    @Override
    public float[] getTexturedCoordinates() {
        return new float[]{
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };
    }
}
