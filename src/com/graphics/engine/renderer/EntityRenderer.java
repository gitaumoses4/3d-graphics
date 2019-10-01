package com.graphics.engine.renderer;

import com.graphics.engine.camera.Camera;
import com.graphics.engine.lighting.Light;
import com.graphics.engine.shaders.StaticShader;

public class EntityRenderer extends StaticRenderer<StaticShader> {
    public EntityRenderer(StaticShader shader) {
        super(shader);
    }

    public EntityRenderer(StaticShader shader, Light light, Camera camera) {
        super(shader, light, camera);
    }
}
