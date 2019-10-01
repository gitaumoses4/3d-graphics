package com.graphics.engine.game;

import com.graphics.engine.GameLogic;
import com.graphics.engine.Window;
import com.graphics.engine.camera.Camera;
import com.graphics.engine.entities.Entity;
import com.graphics.engine.lighting.Light;
import com.graphics.engine.models.Model;
import com.graphics.engine.renderer.EntityRenderer;
import com.graphics.engine.renderer.StaticRenderer;
import com.graphics.engine.renderer.TerrainRenderer;
import com.graphics.engine.shaders.StaticShader;
import com.graphics.engine.shaders.TerrainShader;
import com.graphics.engine.terrain.Terrain;
import com.graphics.maths.Vector3f;

import java.awt.*;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class Game implements GameLogic {
    private EntityRenderer staticRenderer;
    private TerrainRenderer terrainRenderer;
    private Light light = new Light(new Vector3f(0, -1000f, 2000f), new Vector3f(1, 1, 1));
    private Camera camera = new Camera(0, 1f, 0);
    private Model tree;
    private Terrain terrain;
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Terrain> terrains = new ArrayList<>();

    public Game() {

    }

    @Override
    public void init(Window window) throws Exception {
        staticRenderer = new EntityRenderer(new StaticShader(window), light, camera);
        terrainRenderer = new TerrainRenderer(new TerrainShader(window), light, camera);
        tree = new Model("tree.png", "tree");
        window.addMouseListener(camera);

        for (int i = 0; i < 300; i++) {
            entities.add(tree.createEntity(new Vector3f(generate(300f), 0, generate(300f)), 0, 0, 0, 1));
        }

        terrains.add(terrain = new Terrain("grass.jpg", 0, 0));
        terrains.add(terrain = new Terrain("grass.jpg", 1, 0));
        terrains.add(terrain = new Terrain("grass.jpg", 0, 1));
        terrains.add(terrain = new Terrain("grass.jpg", 1, 1));
    }

    private float generate(float cap) {
        return ((float) Math.random() * cap) - ((float) Math.random() * cap);
    }

    @Override
    public void input(Window window) {
        staticRenderer.input(window);
        terrainRenderer.input(window);
    }

    @Override
    public void update(float interval) {
        staticRenderer.update(interval);
        terrainRenderer.update(interval);
    }

    @Override
    public void render(Window window) {
        glEnable(GL_DEPTH_TEST);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        window.setClearColor(new Color(84, 125, 208));
        terrainRenderer.render(window);
        terrainRenderer.render(terrains);
        staticRenderer.render(window);
        staticRenderer.processEntities(entities);
    }

    @Override
    public void finish() {
        staticRenderer.finish();
        terrainRenderer.finish();
    }
}
