package com.graphics.maths;

import com.graphics.engine.camera.Camera;

public class Maths {

    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
        Matrix4f matrix4f = new Matrix4f();
        matrix4f.setIdentity();
        matrix4f = matrix4f.multiply(Matrix4f.translate(translation.x, translation.y, translation.z));
        matrix4f = matrix4f.multiply(Matrix4f.rotate(rx, 1, 0, 0));
        matrix4f = matrix4f.multiply(Matrix4f.rotate(ry, 0, 1, 0));
        matrix4f = matrix4f.multiply(Matrix4f.rotate(rz, 0, 0, 1));
        matrix4f = matrix4f.multiply(Matrix4f.scale(scale, scale, scale));

        return matrix4f;
    }

    public static Matrix4f createViewMatrix(Camera camera) {
        Matrix4f matrix4f = new Matrix4f();
        matrix4f.setIdentity();
        matrix4f = matrix4f.multiply(Matrix4f.rotate(camera.getPitch(), 1, 0, 0));
        matrix4f = matrix4f.multiply(Matrix4f.rotate(camera.getYaw(), 0, 1, 0));
        matrix4f = matrix4f.multiply(Matrix4f.rotate(camera.getRoll(), 0, 0, 1));

        Vector3f position = camera.getPosition();
        position = position.scale(-1);
        matrix4f = matrix4f.multiply(Matrix4f.translate(position.x, position.y, position.z));
        return matrix4f;
    }
}
