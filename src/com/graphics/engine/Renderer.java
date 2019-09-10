package com.graphics.engine;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public abstract class Renderer implements GameLogic {


    private final float[] positions;
    private final int[] indices;
    private RawModel rawModel;
    private Loader loader;

    public Renderer(float[] positions, int[] indices) {
        this.positions = positions;
        this.indices = indices;
    }

    @Override
    public void init() throws Exception {
        loader = new Loader();
        rawModel = loader.loadToVAO(positions, indices);
    }

    public void setRawModel(RawModel rawModel) {
        this.rawModel = rawModel;
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    @Override
    public void render(Window window) {
        window.setClearColor(Color.black);
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
