package com.graphics.engine.terrain;

import com.graphics.engine.Loader;
import com.graphics.engine.entities.Entity;
import com.graphics.engine.models.Model;
import com.graphics.engine.models.RawModel;
import com.graphics.maths.Vector3f;

public class Terrain extends Model {
    private static final float SIZE = 1000;
    private static final int VERTEX_COUNT = 128;
    private final int gridX, gridZ;

    public Terrain(String textureFile, int gridX, int gridZ) {
        this.initialize(Terrain.generateTerrain(this.getLoader()), textureFile);
        this.gridX = gridX;
        this.gridZ = gridZ;
    }

    public Entity create() {
        return this.createEntity(new Vector3f(gridX * SIZE, 0, gridZ * SIZE), 0, 0, 0, 1);
    }

    public static RawModel generateTerrain(Loader loader) {
        int count = VERTEX_COUNT * VERTEX_COUNT;
        float[] vertices = new float[count * 3];
        float[] normals = new float[count * 3];
        float[] textureCoords = new float[count * 2];
        int[] indices = new int[6 * (VERTEX_COUNT - 1) * (6 * VERTEX_COUNT)];

        int vertexPointer = 0;
        int pointer = 0;
        for (int i = 0; i < VERTEX_COUNT; i++) {
            for (int j = 0; j < VERTEX_COUNT; j++) {
                vertices[vertexPointer * 3] = -((float) j * SIZE) / (VERTEX_COUNT - 1);
                vertices[vertexPointer * 3 + 1] = 0;
                vertices[vertexPointer * 3 + 2] = -((float) i * SIZE) / (VERTEX_COUNT - 1);

                normals[vertexPointer * 3] = 0;
                normals[vertexPointer * 3 + 1] = 1;
                normals[vertexPointer * 3 + 2] = 2;

                textureCoords[vertexPointer * 2] = ((float) j) / (VERTEX_COUNT - 1);
                textureCoords[vertexPointer * 2 + 1] = ((float) i) / (VERTEX_COUNT - 1);
                vertexPointer++;
            }
        }

        for (int i = 0; i < VERTEX_COUNT - 1; i++) {
            for (int j = 0; j < VERTEX_COUNT - 1; j++) {
                int topLeft = (i * VERTEX_COUNT) + j;
                int topRight = (topLeft + 1);
                int bottomLeft = ((i + 1) * VERTEX_COUNT) + j;
                int bottomRight = bottomLeft + 1;
                indices[pointer++] = topLeft;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = topRight;
                indices[pointer++] = topRight;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = bottomRight;
            }
        }

        return loader.loadToVAO(vertices, textureCoords, normals, indices);
    }
}
