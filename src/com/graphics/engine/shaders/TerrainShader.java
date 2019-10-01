package com.graphics.engine.shaders;

import com.graphics.engine.Window;

public class TerrainShader extends StaticShader {
    public TerrainShader(Window window) {
        super(window, "terrainVertex", "terrainFragment");
    }
}
