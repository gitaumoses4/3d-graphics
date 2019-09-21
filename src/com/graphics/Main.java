package com.graphics;

import com.graphics.engine.GameEngine;
import com.graphics.engine.Renderer;
import com.graphics.engine.lighting.Light;
import com.graphics.engine.models.Model;
import com.graphics.engine.shaders.StaticShader;
import com.graphics.engine.Window;
import com.graphics.maths.Vector3f;

import java.awt.*;


public class Main {

    private final GameEngine gameEngine;
    private Light light = new Light(new Vector3f(0, 0, -20f), new Vector3f(1, 0, 1));

    private Main() {
        Renderer renderer = new Renderer() {
            StaticShader staticShader;

            @Override
            public void init(Window window) throws Exception {
                window.setClearColor(Color.red);
                staticShader = new StaticShader(window);
                Model model = new Model("dragon");
                addEntity(model.createEntity(new Vector3f(0, -4f, -25f), 0, 0, 0, 1f), staticShader);
            }

            @Override
            public void input(Window window) {

            }

            @Override
            public void update(float interval) {
                if (hasEntities()) {
//                    entity.move(0.002f, 0, 0);
                    getEntity(0).rotate(0f, 1.7f, 0f);
                }
                staticShader.loadLight(light);
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
