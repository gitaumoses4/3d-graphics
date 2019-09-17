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
            Entity entity;

            @Override
            public void init(Window window) throws Exception {
                entity = new Cube().createEntity(new Vector3f(0, 0, -2f), 0, 0, 0, 1f);
                addEntity(entity, new StaticShader(window));
            }

            @Override
            public void input(Window window) {

            }

            @Override
            public void update(float interval) {
                if (entity != null) {
//                    entity.move(0.002f, 0, 0);
                    entity.rotate(0.4f, 1.7f, 1.3f);
                }
            }

            @Override
            public void finish() {

            }
        };
        gameEngine = new GameEngine("3D Game Engine", 1280, 720, true, renderer);
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.gameEngine.run();
    }
}
