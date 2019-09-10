package com.graphics;

import com.graphics.engine.GameEngine;
import com.graphics.engine.Renderer;
import com.graphics.games.Rectangle;


public class Main {

    private final Renderer renderer = new Rectangle();
    private final GameEngine gameEngine;

    private Main() {
        gameEngine = new GameEngine("3D Game Engine", 1280, 720, true, renderer);
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.gameEngine.run();
    }
}
