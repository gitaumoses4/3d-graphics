package com.graphics.engine.models;

import com.graphics.engine.Loader;
import com.graphics.engine.OBJLoader;
import com.graphics.engine.entities.Entity;
import com.graphics.engine.textures.ModelTexture;
import com.graphics.maths.Vector3f;

public class Model {

    private TexturedModel texturedModel;
    public Loader loader = new Loader();


    public Model(String modelFile) {
        this.initialize(OBJLoader.loadObjModel(modelFile, loader), null);
    }

    public Model(String textureFile, String modelFile) {
        this.initialize(OBJLoader.loadObjModel(modelFile, loader), textureFile);
    }

    public Model(String textureFile, float[] positions, float[] textures, float[] normals, int[] indices) {
        RawModel rawModel = loader.loadToVAO(positions, textures, normals, indices);
        this.initialize(rawModel, textureFile);
    }

    public Model(String textureFile, RawModel rawModel) {
        this.initialize(rawModel, textureFile);
    }

    public Model() {

    }

    public void setLoader(Loader loader) {
        this.loader = loader;
    }

    public void initialize(RawModel rawModel, String texture) throws NullPointerException {
        if (rawModel == null) {
            throw new NullPointerException("Unable to initialize model");
        } else {
            ModelTexture modelTexture = new ModelTexture(loader.loadTexture(texture));
            texturedModel = new TexturedModel(rawModel, modelTexture);
        }
    }

    public Entity createEntity(Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        return new Entity(texturedModel, position, rotX, rotY, rotZ, scale);
    }

    public Entity createEntity() {
        return new Entity(texturedModel, new Vector3f(0, 0, 0), 0, 0, 0, 1);
    }

    public Loader getLoader() {
        return loader;
    }

    public void dispose() {
        this.loader.cleanUp();
    }
}
