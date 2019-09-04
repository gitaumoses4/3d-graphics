package com.graphics.engine;

public class GameEngine implements Runnable {

    public static final int TARGET_FPS = 75;
    public static final int TARGET_UPS = 30;

    private final Window window;
    private final Timer timer;
    private final GameLogic gameLogic;

    public GameEngine(String windowTitle, int width, int height, boolean vSync, GameLogic gameLogic) throws Exception {
        window = new Window(windowTitle, width, height, vSync);
        this.gameLogic = gameLogic;
        this.timer = new Timer();
    }


    @Override
    public void run() {
        try {
            init();
            gameLoop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void init(){

    }

    public void gameLoop(){

    }
}
