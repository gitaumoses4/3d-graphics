package com.graphics.engine.models;

import com.graphics.engine.Loader;
import com.graphics.engine.textures.ModelTexture;

public class TexturedModel {
    private final Loader loader;
    private RawModel rawModel;
    private ModelTexture modelTexture;

    public TexturedModel(RawModel rawModel, ModelTexture modelTexture, Loader loader) {
        this.rawModel = rawModel;
        this.modelTexture = modelTexture;
        this.loader = loader;
    }

    public Loader getLoader() {
        return loader;
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    public ModelTexture getModelTexture() {
        return modelTexture;
    }
}
