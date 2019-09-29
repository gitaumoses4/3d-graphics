package com.graphics.engine.textures;

public class ModelTexture {

    private int textureID;
    private float shineDamper = 1f;
    private float reflectivity = 0f;

    public ModelTexture(int textureID) {
        this.textureID = textureID;
    }

    public int getID() {
        return textureID;
    }

    public float getShineDamper() {
        return shineDamper;
    }

    public float getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
    }

    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }
}
