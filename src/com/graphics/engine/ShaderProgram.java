package com.graphics.engine;

import com.graphics.utils.Utils;

import static org.lwjgl.opengl.GL20.*;

public abstract class ShaderProgram {

    private int programID, vertexShaderID, fragmentShaderID;

    public ShaderProgram(String vertexFile, String fragmentFile) {
        try {
            vertexShaderID = ShaderProgram.loadShader(vertexFile, GL_VERTEX_SHADER);
            fragmentShaderID = ShaderProgram.loadShader(fragmentFile, GL_FRAGMENT_SHADER);

            programID = glCreateProgram();
            glAttachShader(programID, vertexShaderID);
            glAttachShader(programID, fragmentShaderID);

            glLinkProgram(programID);
            glValidateProgram(programID);
            bindAttributes();
        } catch (Exception ignore) {

        }
    }

    public void start() {
        glUseProgram(programID);
    }

    public void stop() {
        glUseProgram(0);
    }

    public void cleanUp() {
        stop();
        glDetachShader(programID, vertexShaderID);
        glDetachShader(programID, fragmentShaderID);
        glDeleteShader(vertexShaderID);
        glDeleteShader(fragmentShaderID);
        glDeleteProgram(programID);
    }

    protected void bindAttribute(int attribute, String variableName) {
        glBindAttribLocation(programID, attribute, variableName);
    }

    protected abstract void bindAttributes();

    private static int loadShader(String file, int type) throws Exception {
        int shaderID = glCreateShader(type);
        glShaderSource(shaderID, Utils.loadResource(file));
        glCompileShader(shaderID);

        if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.out.println(glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader.");
            System.exit(-1);
        }
        return shaderID;
    }
}
