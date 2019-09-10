package com.graphics.engine;

import com.graphics.utils.Utils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public abstract class Renderer implements GameLogic {


    private final float[] positions;
    private final int[] indices;
    private RawModel rawModel;
    private Loader loader;
    private int programId;

    public Renderer(float[] positions, int[] indices) {
        this.positions = positions;
        this.indices = indices;
    }

    @Override
    public void init() throws Exception {
        loader = new Loader();
        rawModel = loader.loadToVAO(positions, indices);
        programId = glCreateProgram();

        int vertexShaderId = createShader(Utils.loadResource("vertex.glsl"), GL_VERTEX_SHADER);
        int fragmentShaderId = createShader(Utils.loadResource("fragment.glsl"), GL_FRAGMENT_SHADER);

        glLinkProgram(programId);
        glDetachShader(programId, vertexShaderId);
        glDetachShader(programId, fragmentShaderId);
        glValidateProgram(programId);
    }

    public void setRawModel(RawModel rawModel) {
        this.rawModel = rawModel;
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    private int createShader(String shaderCode, int shaderType) {
        int shaderId = glCreateShader(shaderType);
        glShaderSource(shaderId, shaderCode);
        glCompileShader(shaderId);

        glAttachShader(programId, shaderId);
        return shaderId;
    }

    @Override
    public void render(Window window) {
        window.setClearColor(Color.black);
        glUseProgram(programId);
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);

        glDrawElements(GL_TRIANGLES, rawModel.getVertexCount(), GL_UNSIGNED_INT, 0);

        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }

    @Override
    public void finish() {
        loader.cleanUp();
    }
}
