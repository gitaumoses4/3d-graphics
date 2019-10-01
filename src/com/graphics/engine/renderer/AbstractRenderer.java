package com.graphics.engine.renderer;

import com.graphics.engine.GameLogic;
import com.graphics.engine.Window;
import com.graphics.engine.camera.Camera;
import com.graphics.engine.entities.Entity;
import com.graphics.engine.lighting.Light;
import com.graphics.engine.models.RawModel;
import com.graphics.engine.models.TexturedModel;
import com.graphics.engine.shaders.ShaderProgram;
import org.lwjgl.opengl.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;

public abstract class AbstractRenderer<T extends ShaderProgram> implements GameLogic {

    private Map<TexturedModel, List<Entity>> entities = new HashMap<>();
    private Window window;
    private T shader;
    private Light light;
    private Camera camera;

    public AbstractRenderer(T shader) {
        this.shader = shader;
    }

    public AbstractRenderer(T shader, Light light, Camera camera) {
        this(shader);
        this.light = light;
        this.camera = camera;
    }

    public T getShader() {
        return shader;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Camera getCamera() {
        return camera;
    }

    public Light getLight() {
        return light;
    }

    @Override
    public void init(Window window) throws Exception {
        GL11.glEnable(GL_CULL_FACE);
        GL11.glCullFace(GL_BACK);
    }

    @Override
    public void render(Window window) {
        this.window = window;
        shader.start();
        loadShaderVariables(shader, light, camera);
        entities.forEach(((texturedModel, entities) -> {
            prepareTexturedModel(texturedModel);
            entities.forEach(entity -> {
                prepareInstance(entity);
                glDrawElements(GL_TRIANGLES, texturedModel.getRawModel().getVertexCount(), GL_UNSIGNED_INT, 0);
            });
            unbindTexturedModel();
        }));
        shader.stop();
        entities.clear();
    }

    public void processEntity(Entity entity) {
        TexturedModel texturedModel = entity.getTexturedModel();
        List<Entity> batch = entities.computeIfAbsent(texturedModel, k -> new ArrayList<>());
        batch.add(entity);
    }

    public void processEntities(List<Entity> entities) {
        entities.forEach(this::processEntity);
    }

    private void prepareTexturedModel(TexturedModel model) {
        RawModel rawModel = model.getRawModel();
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        loadShaderVariables(shader, model);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL_TEXTURE_2D, model.getModelTexture().getID());
    }

    public abstract void loadShaderVariables(T shader, Light light, Camera camera);

    public abstract void loadShaderVariables(T shader, TexturedModel model);

    public abstract void loadShaderVariables(T shader, Entity entity);

    private void unbindTexturedModel() {
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void prepareInstance(Entity entity) {
        loadShaderVariables(shader, entity);
    }

    @Override
    public void finish() {
        shader.cleanUp();
    }
}
