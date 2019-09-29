package com.graphics.engine.renderer;

import com.graphics.engine.Window;
import com.graphics.engine.camera.Camera;
import com.graphics.engine.entities.Entity;
import com.graphics.engine.lighting.Light;
import com.graphics.engine.models.TexturedModel;
import com.graphics.engine.shaders.StaticShader;
import com.graphics.maths.Maths;
import com.graphics.maths.Matrix4f;

import static com.graphics.engine.shaders.StaticShader.*;

public class StaticRenderer extends AbstractRenderer<StaticShader> {

    public StaticRenderer(StaticShader shader) {
        super(shader);
    }

    public StaticRenderer(StaticShader shader, Light light, Camera camera) {
        super(shader, light, camera);
    }

    @Override
    public void loadShaderVariables(StaticShader shader, Light light, Camera camera) {
        shader.load(PROJECTION_MATRIX, shader.getProjectionMatrix());
        shader.load(VIEW_MATRIX, Maths.createViewMatrix(camera));
        shader.load(LIGHT_POSITION, light.getPosition());
        shader.load(LIGHT_COLOR, light.getColor());
    }

    @Override
    public void loadShaderVariables(StaticShader shader, TexturedModel model) {
        shader.load(SHINE_DAMPER, model.getModelTexture().getShineDamper());
        shader.load(REFLECTIVITY, model.getModelTexture().getReflectivity());
    }

    @Override
    public void loadShaderVariables(StaticShader shader, Entity entity) {
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
        shader.load(TRANSFORMATION_MATRIX, transformationMatrix);
    }

    @Override
    public void input(Window window) {
        getCamera().move(window);
    }

    @Override
    public void update(float interval) {

    }

    @Override
    public void finish() {

    }
}
