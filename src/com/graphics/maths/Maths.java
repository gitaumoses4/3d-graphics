package com.graphics.maths;

public class Maths {

    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
        Matrix4f matrix4f = new Matrix4f();
        matrix4f.setIdentity();
        matrix4f = matrix4f.multiply(Matrix4f.translate(translation.x, translation.y, translation.z));
        matrix4f = matrix4f.multiply(Matrix4f.rotate((float) Math.toRadians(rx), 1, 0, 0));
        matrix4f = matrix4f.multiply(Matrix4f.rotate((float) Math.toRadians(ry), 0, 1, 0));
        matrix4f = matrix4f.multiply(Matrix4f.rotate((float) Math.toRadians(rz), 0, 0, 1));
        matrix4f = matrix4f.multiply(Matrix4f.scale(scale, scale, scale));

        return matrix4f;
    }
}
