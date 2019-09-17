package com.graphics.engine;

import com.graphics.maths.Matrix4f;

public class StaticShader extends ShaderProgram {

    private int location_transformationMatrix;

    public StaticShader() {
        super("vertex.glsl", "fragment.glsl");
    }

    @Override
    protected void bindAttributes() {
        bindAttribute(0, "position");
        bindAttribute(1, "textureCoords");
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = getUniformLocation("transformationMatrix");
    }

    public void loadTransformationMatrix(Matrix4f matrix4f) {
        super.load(location_transformationMatrix, matrix4f);
    }
}
