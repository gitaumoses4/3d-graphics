package com.graphics.engine.models;

import com.graphics.engine.Loader;

public class RawModel {

    private int vaoID;
    private int vertexCount;
    private Loader loader;

    public RawModel(int vaoID, int vertexCount, Loader loader) {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
        this.loader = loader;
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void setVaoID(int vaoID) {
        this.vaoID = vaoID;
    }

    public void setVertexCount(int vertexCount) {
        this.vertexCount = vertexCount;
    }

    public Loader getLoader() {
        return loader;
    }

    public void setLoader(Loader loader) {
        this.loader = loader;
    }
}
