package com.graphics;

import com.graphics.engine.GameEngine;
import com.graphics.engine.game.Game;


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
