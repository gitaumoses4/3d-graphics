package com.graphics;

import com.graphics.engine.GameEngine;
import com.graphics.engine.Workspace;


public class Main {

    private final GameEngine gameEngine;
    private final Workspace workspace;

    private Main() throws Exception {
        workspace = new Workspace();
        gameEngine = new GameEngine("3D Game Engine", 1280, 720, true, workspace.getDummyGame());
    }

    public void draw() {
        workspace.draw();
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.gameEngine.run();
    }
}
