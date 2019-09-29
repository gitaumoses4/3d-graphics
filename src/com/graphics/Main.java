package com.graphics;

import com.graphics.engine.GameEngine;
import com.graphics.engine.game.Game;
import com.graphics.engine.renderer.AbstractRenderer;
import com.graphics.engine.models.Model;
import com.graphics.engine.renderer.StaticRenderer;
import com.graphics.engine.shaders.StaticShader;
import com.graphics.engine.Window;
import com.graphics.maths.Vector3f;


public class Main {

    private final GameEngine gameEngine;

    private Main() {
        Game game = new Game();
        gameEngine = new GameEngine("3D Game Engine", 1280, 720, true, game);
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.gameEngine.run();
    }
}
