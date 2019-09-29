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

    public static final String TRANSFORMATION_MATRIX = "transformationMatrix";
    public static final String PROJECTION_MATRIX = "projectionMatrix";
    public static final String VIEW_MATRIX = "viewMatrix";
    public static final String LIGHT_POSITION = "lightPosition";
    public static final String LIGHT_COLOR = "lightColor";
    public static final String SHINE_DAMPER = "shineDamper";
    public static final String REFLECTIVITY = "reflectivity";

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
    protected String[] getAllUniformLocations() {
        return new String[]{
                TRANSFORMATION_MATRIX, PROJECTION_MATRIX,
                VIEW_MATRIX, LIGHT_POSITION,
                LIGHT_COLOR, SHINE_DAMPER,
                REFLECTIVITY
        };
    }

    public Matrix4f getProjectionMatrix() {
        float aspect = ((float) window.getWidth()) / window.getHeight();
        return Matrix4f.perspective(FOV, aspect, NEAR_PLANE, FAR_PLANE);
    }
}
