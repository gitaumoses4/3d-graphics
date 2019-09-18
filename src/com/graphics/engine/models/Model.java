package com.graphics.engine.models;

import com.graphics.engine.Loader;
import com.graphics.engine.entities.Entity;
import com.graphics.engine.textures.ModelTexture;
import com.graphics.maths.Vector3f;

public abstract class Model {

    private TexturedModel texturedModel;

    public Model() {
        Loader loader = new Loader();
        RawModel rawModel = loader.loadToVAO(getPositionCoordinates(), getIndices(), getTexturedCoordinates());
        ModelTexture modelTexture = new ModelTexture(loader.loadTexture("wood.png"));
        texturedModel = new TexturedModel(rawModel, modelTexture, loader);
    }

    public abstract float[] getPositionCoordinates();

    public abstract int[] getIndices();

    public abstract float[] getTexturedCoordinates();

    public Entity createEntity(Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        return new Entity(texturedModel, position, rotX, rotY, rotZ, scale);
    }

    public Entity createEntity() {
        return new Entity(texturedModel, new Vector3f(0, 0, 0), 0, 0, 0, 1);
    }
}
