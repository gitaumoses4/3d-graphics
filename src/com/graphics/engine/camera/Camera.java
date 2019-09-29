package com.graphics.engine.camera;

import com.graphics.engine.Window;
import com.graphics.engine.events.MouseListener;
import com.graphics.maths.Vector3f;

import java.awt.event.KeyEvent;

import static org.lwjgl.glfw.GLFW.*;

public class Camera implements MouseListener {
    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch;
    private float yaw;
    private float roll;

    public Vector3f getPosition() {
        return position;
    }

    public void move(Window window) {
        float diff = 1f;
        if (window.isKeyPressed(GLFW_KEY_W)) {
            position.z -= diff;
        } else if (window.isKeyPressed(GLFW_KEY_A)) {
            position.x -= diff;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            position.z += diff;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            position.x += diff;
        } else if (window.isKeyPressed(GLFW_KEY_UP)) {
            position.y += diff;
        } else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            position.y -= diff;
        }
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }

    @Override
    public void onMousePressed(float x, float y) {

    }

    @Override
    public void onMouseDragged(float x, float y, float dx, float dy) {

    }

    @Override
    public void onMouseMoved(float x, float y, float dx, float dy) {

    }

    @Override
    public void onMouseScroll(float dx, float dy) {
        position.z += dy;
    }
}
