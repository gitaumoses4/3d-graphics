package com.graphics.utils;

public class Matrix {

    private float[][] matrix;
    private int rows, cols;

    public Matrix(int rows, int cols) {
        matrix = new float[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    public Matrix(int rows, int cols, float... values) {
        this(rows, cols);
        for (int i = 0; i < values.length; i++) {
            matrix[i / cols][i % cols] = values[i];
        }
    }

    public Matrix(int rows, int cols, float[][] values) {
        this(rows, cols);
        if (values.length == rows && values[0].length == cols) {
            ArrayStream.of(values).write(this.matrix, ((i, j, value) -> {
                return value;
            }));
        }
    }

    public Matrix set(int row, int col, float value) {
        this.matrix[row][col] = value;
        return this;
    }

    public float get(int row, int col) {
        return this.matrix[row][col];
    }

    public Matrix multiply(Matrix another) {
        int m = this.rows;
        int n = this.cols;
        int _n = another.rows;
        int p = another.cols;

        if (n != _n) {
            throw new IllegalArgumentException("Matrix rows and columns do not match");
        }
        Matrix result = new Matrix(m, p);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                float value = 0;
                for (int k = 0; k < n; k++) {
                    value += (this.get(i, k) * another.get(k, j));
                }
                result.set(i, j, value);
            }
        }
        return result;
    }

    public Matrix multiply(float scalar) {
        Matrix m = new Matrix(rows, cols);
        ArrayStream.of(this.matrix).read(((i, j, value) -> {
            m.set(i, j, value * scalar);
        }));
        return m;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        int numDigits = 2;

        final int[] length = {1};
        ArrayStream.of(matrix).read(((i, j, value) -> {
            int intValue = (int) value;
            length[0] = Math.max(length[0], String.valueOf(intValue).length());
        }));
        length[0] += numDigits + 1;

        ArrayStream.of(matrix).read(((i, j, value) -> {
            b.append(String.format("%" + (length[0] + 1) + "." + numDigits + "f", value));
            if (j == cols - 1) {
                b.append("\n");
            }
        }));
        return b.toString();
    }
}