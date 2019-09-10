package com.graphics.engine;

public interface GameLogic {
    void init() throws Exception;

    void input(Window window);

    void update(float interval);

    void render(Window window);

    void finish();
}
