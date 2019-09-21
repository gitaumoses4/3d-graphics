package com.graphics.engine;

import com.graphics.engine.camera.Camera;
import com.graphics.engine.entities.Entity;
import com.graphics.engine.lighting.Light;
import com.graphics.engine.models.RawModel;
import com.graphics.engine.models.TexturedModel;
import com.graphics.engine.shaders.StaticShader;
import com.graphics.maths.Maths;
import com.graphics.maths.Matrix4f;
import com.graphics.maths.Vector3f;
import org.lwjgl.opengl.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;

public abstract class Renderer implements GameLogic {

    private HashMap<Entity, StaticShader> entities = new HashMap<>();
    private ArrayList<Entity> entityList = new ArrayList<>();

    private Camera camera = new Camera();
    private Light light = new Light(new Vector3f(0, 0, 0), new Vector3f(1, 0, 1));
    private Window window;

    public Renderer() {
    }


    public void addEntity(Entity entity, StaticShader shaderProgram) {
        this.entities.put(entity, shaderProgram);
        entityList.add(entity);
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
        entityList.remove(entity);
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Camera getCamera() {
        return camera;
    }

    public void clear() {
        this.entities.clear();
        entityList.clear();
    }

    public Entity getEntity(int index) {
        return entityList.get(index);
    }

    public boolean hasEntities() {
        return !this.entities.isEmpty();
    }

    public ArrayList<Entity> allEntities() {
        return entityList;
    }

    @Override
    public void render(Window window) {
        this.window = window;
        glEnable(GL_DEPTH_TEST);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        entities.forEach((this::render));
    }

    public void render(Entity entity, StaticShader shaderProgram) {
        if (entity != null) {
            shaderProgram.start();
            TexturedModel texturedModel = entity.getTexturedModel();
            RawModel rawModel = texturedModel.getRawModel();
            GL30.glBindVertexArray(rawModel.getVaoID());
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            GL20.glEnableVertexAttribArray(2);

            Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());

            shaderProgram.loadTransformationMatrix(transformationMatrix);
            shaderProgram.loadProjectionMatrix();
            shaderProgram.loadViewMatrix(camera);
            shaderProgram.loadLight(light);
            camera.move(window);

            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL_TEXTURE_2D, texturedModel.getModelTexture().getID());

            glDrawElements(GL_TRIANGLES, rawModel.getVertexCount(), GL_UNSIGNED_INT, 0);

            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL20.glDisableVertexAttribArray(2);
            GL30.glBindVertexArray(0);
            shaderProgram.stop();
        }
    }
}
