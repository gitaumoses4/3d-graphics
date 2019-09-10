package com.graphics;

import com.graphics.engine.DummyGame;
import com.graphics.engine.GameEngine;


public class Main {

    private final GameEngine gameEngine;

    private Main() {
        gameEngine = new GameEngine("3D Game Engine", 1280, 720, true,  new DummyGame());
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.gameEngine.run();
    }
}
