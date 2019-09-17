package com.graphics.engine;

import com.graphics.engine.models.RawModel;
import com.graphics.engine.models.TexturedModel;
import com.graphics.engine.textures.ModelTexture;
import org.lwjgl.opengl.*;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public abstract class Renderer implements GameLogic {


    private final float[] positions;
    private final int[] indices;
    private final float[] textureCoords;
    private RawModel rawModel;
    private ModelTexture modelTexture;
    private TexturedModel texturedModel;
    private Loader loader;

    public Renderer(float[] positions, int[] indices, float[] textureCoords) {
        this.positions = positions;
        this.indices = indices;
        this.textureCoords = textureCoords;
    }

    @Override
    public void init() throws Exception {
        loader = new Loader();
        rawModel = loader.loadToVAO(positions, indices, textureCoords);
        modelTexture = new ModelTexture(loader.loadTexture("wood.png"));
        texturedModel = new TexturedModel(rawModel, modelTexture);
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
        GL20.glEnableVertexAttribArray(1);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL_TEXTURE_2D, texturedModel.getModelTexture().getID());

        glDrawElements(GL_TRIANGLES, rawModel.getVertexCount(), GL_UNSIGNED_INT, 0);

        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }

    @Override
    public void finish() {
        loader.cleanUp();
    }
}
