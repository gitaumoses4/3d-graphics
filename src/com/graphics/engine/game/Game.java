package com.graphics.engine.game;

import com.graphics.engine.GameLogic;
import com.graphics.engine.Window;
import com.graphics.engine.camera.Camera;
import com.graphics.engine.entities.Entity;
import com.graphics.engine.lighting.Light;
import com.graphics.engine.models.Model;
import com.graphics.engine.renderer.StaticRenderer;
import com.graphics.engine.shaders.StaticShader;
import com.graphics.maths.Vector3f;

import java.util.ArrayList;

public class Game implements GameLogic {
    private StaticRenderer staticRenderer;
    private Light light = new Light(new Vector3f(0, -1000f, 2000f), new Vector3f(1, 1, 1));
    private Camera camera = new Camera();
    private Model dragon;
    private ArrayList<Entity> entities = new ArrayList<>();

    public Game() {

    }

    @Override
    public void init(Window window) throws Exception {
        staticRenderer = new StaticRenderer(new StaticShader(window), light, camera);
        dragon = new Model("wood.png", "cube");
        window.addMouseListener(camera);
        for (int i = 0; i < 100; i++) {
            entities.add(dragon.createEntity(new Vector3f(generate(300f), generate(200f), generate(250f)), 0, 0, 0, generate(10)));
        }
    }

    private float generate(float cap) {
        return ((float) Math.random() * cap) - ((float) Math.random() * cap);
    }

    @Override
    public void input(Window window) {
        staticRenderer.input(window);
    }

    @Override
    public void update(float interval) {
        staticRenderer.update(interval);
    }

    @Override
    public void render(Window window) {
        staticRenderer.render(window);
        entities.forEach(entity -> staticRenderer.processEntity(entity));
    }

    @Override
    public void finish() {
        staticRenderer.finish();
    }
}
