package com.graphics.engine.renderer;

import com.graphics.engine.camera.Camera;
import com.graphics.engine.lighting.Light;
import com.graphics.engine.shaders.TerrainShader;
import com.graphics.engine.terrain.Terrain;

import java.util.List;

public class TerrainRenderer extends StaticRenderer<TerrainShader> {
    public TerrainRenderer(TerrainShader shader) {
        super(shader);
    }

    public TerrainRenderer(TerrainShader shader, Light light, Camera camera) {
        super(shader, light, camera);
    }

    public void processTerrain(Terrain terrain) {
        processEntity(terrain.create());
    }

    public void render(List<Terrain> terrains) {
        terrains.forEach(terrain -> processEntity(terrain.create()));
    }
}
