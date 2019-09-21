package com.graphics.engine.shaders;

import com.graphics.engine.Window;
import com.graphics.engine.camera.Camera;
import com.graphics.engine.lighting.Light;
import com.graphics.maths.Maths;
import com.graphics.maths.Matrix4f;

public class StaticShader extends ShaderProgram {

    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;
    private final Window window;

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int location_lightPosition;
    private int location_lightColor;

    public StaticShader(Window window) {
        super("vertex", "fragment");
        this.window = window;
    }

    @Override
    protected void bindAttributes() {
        bindAttribute(0, "position");
        bindAttribute(1, "textureCoords");
        bindAttribute(2, "normal");
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = getUniformLocation("transformationMatrix");
        location_projectionMatrix = getUniformLocation("projectionMatrix");
        location_viewMatrix = getUniformLocation("viewMatrix");
        location_lightPosition = getUniformLocation("lightPosition");
        location_lightColor = getUniformLocation("lightColor");
    }

    public void loadTransformationMatrix(Matrix4f matrix4f) {
        super.load(location_transformationMatrix, matrix4f);
    }

    public void loadProjectionMatrix() {
        float aspect = ((float) window.getWidth()) / window.getHeight();
        Matrix4f projectionMatrix = Matrix4f.perspective(FOV, aspect, NEAR_PLANE, FAR_PLANE);

        super.load(location_projectionMatrix, projectionMatrix);
    }

    public void loadViewMatrix(Camera camera) {
        super.load(location_viewMatrix, Maths.createViewMatrix(camera));
    }

    public void loadLight(Light light) {
        super.load(location_lightPosition, light.getPosition());
        super.load(location_lightColor, light.getColor());
    }
}
