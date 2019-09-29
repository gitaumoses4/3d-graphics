package com.graphics.engine.shaders;

import com.graphics.maths.Matrix4f;
import com.graphics.maths.Vector3f;
import com.graphics.utils.Utils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;
import java.util.HashMap;

import static org.lwjgl.opengl.GL20.*;

public abstract class ShaderProgram {

    private FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
    private int programID, vertexShaderID, fragmentShaderID;
    private HashMap<String, Integer> locations = new HashMap<>();

    public ShaderProgram(String vertexFile, String fragmentFile) {
        try {
            vertexShaderID = ShaderProgram.loadShader(vertexFile, GL_VERTEX_SHADER);
            fragmentShaderID = ShaderProgram.loadShader(fragmentFile, GL_FRAGMENT_SHADER);

            programID = glCreateProgram();
            glAttachShader(programID, vertexShaderID);
            glAttachShader(programID, fragmentShaderID);

            bindAttributes();

            glLinkProgram(programID);
            glValidateProgram(programID);
            for (String location : getAllUniformLocations()) {
                locations.put(location, getUniformLocation(location));
            }
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
        glShaderSource(shaderID, Utils.loadShader(file));
        glCompileShader(shaderID);

        if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.out.println(glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader.");
            System.exit(-1);
        }
        return shaderID;
    }

    protected int getUniformLocation(String uniformName) {
        return GL20.glGetUniformLocation(programID, uniformName);
    }

    protected abstract String[] getAllUniformLocations();

    public void load(String location, float value) {
        GL20.glUniform1f(locations.get(location), value);
    }

    public void load(String location, Vector3f vector) {
        GL20.glUniform3f(locations.get(location), vector.x, vector.y, vector.z);
    }

    public void load(String location, boolean value) {
        GL20.glUniform1f(locations.get(location), value ? 1f : 0f);
    }

    public void load(String location, Matrix4f value) {
        value.toBuffer(matrixBuffer);
        GL20.glUniformMatrix4fv(locations.get(location), false, matrixBuffer);
    }

}
