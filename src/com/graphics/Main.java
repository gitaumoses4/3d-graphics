package com.graphics;

import com.graphics.engine.GameEngine;
import com.graphics.engine.Renderer;
import com.graphics.engine.shaders.StaticShader;
import com.graphics.engine.Window;
import com.graphics.engine.entities.Entity;
import com.graphics.games.Cube;
import com.graphics.games.Rectangle;
import com.graphics.maths.Vector3f;


public class Main {

    private final GameEngine gameEngine;

    private Main() {
        Renderer renderer = new Renderer() {

            @Override
            public void init(Window window) throws Exception {
                StaticShader staticShader = new StaticShader(window);
                Cube cube = new Cube();
                addEntity(cube.createEntity(new Vector3f(0, 0, -4f), 0, 0, 0, 1f), staticShader);
                addEntity(cube.createEntity(new Vector3f(1f, 0.4f, -2f), 0, 0, 0, 1f), staticShader);
            }

            @Override
            public void input(Window window) {

            }

            @Override
            public void update(float interval) {
                if (hasEntities()) {
//                    entity.move(0.002f, 0, 0);
                    getEntity(0).rotate(0.4f, 1.7f, 1.3f);
                    getEntity(1).rotate(0.2f, 3.7f, 1f);
                }
            }

            @Override
            public void finish() {
                if (hasEntities()) {
                    allEntities().forEach(entity -> entity.getTexturedModel().getLoader().cleanUp());
                }
            }
        };
        gameEngine = new GameEngine("3D Game Engine", 1280, 720, true, renderer);
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.gameEngine.run();
    }
}
