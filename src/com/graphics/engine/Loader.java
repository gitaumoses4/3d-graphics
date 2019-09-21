package com.graphics.engine;

import com.graphics.engine.models.RawModel;
import com.graphics.utils.Utils;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

public class Loader {

    private List<Integer> vaos = new ArrayList<>();
    private List<Integer> vbos = new ArrayList<>();
    private List<Integer> textures = new ArrayList<>();

    public RawModel loadToVAO(float[] positions, float[] textureCoords, float[] normals, int[] indices) {
        int vaoID = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, 3, positions);
        storeDataInAttributeList(1, 2, textureCoords);
        storeDataInAttributeList(2, 3, normals);
        unbindVAO();
        return new RawModel(vaoID, indices.length, this);
    }

    public RawModel loadToVAO(float[] positions) {
        int vaoID = createVAO();
        storeDataInAttributeList(0, 3, positions);
        unbindVAO();
        return new RawModel(vaoID, positions.length / 3, this);
    }

    private int createVAO() {
        int vaoID = glGenVertexArrays();
        vaos.add(vaoID);
        glBindVertexArray(vaoID);
        return vaoID;
    }

    private void storeDataInAttributeList(int attributeIndex, int coordinateSize, float[] data) {
        int vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = Utils.toBuffer(data);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glVertexAttribPointer(attributeIndex, coordinateSize, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        vbos.add(vboID);
    }


    public int loadTexture(String fileName) {
        fileName = fileName == null || fileName.isEmpty() ? "white.png" : fileName;
        int textureID = 0;
        try {
            Texture texture = new Texture(fileName);
            textureID = texture.getId();
            textures.add(textureID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textureID;
    }

    private void bindIndicesBuffer(int[] indices) {
        int vboID = glGenBuffers();
        vbos.add(vboID);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = Utils.toBuffer(indices);

        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
    }

    public void cleanUp() {
        vaos.forEach(GL30::glDeleteVertexArrays);
        vbos.forEach(GL30::glDeleteBuffers);
        textures.forEach(GL30::glDeleteTextures);
    }

    private void unbindVAO() {
        glBindVertexArray(0);
    }
}
