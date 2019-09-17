package com.graphics.engine;

public class StaticShader extends ShaderProgram {

    public StaticShader() {
        super("vertex.glsl", "fragment.glsl");
    }

    @Override
    protected void bindAttributes() {
        bindAttribute(0, "position");
        bindAttribute(1, "textureCoords");
    }
}
