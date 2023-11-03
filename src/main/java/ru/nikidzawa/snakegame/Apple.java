package ru.nikidzawa.snakegame;

import ru.nikidzawa.snakegame.config.GameObject;
import ru.nikidzawa.snakegame.config.RetroGameEngine;

public class Apple extends GameObject {
    public boolean isAlive = true;
    private final static String APPLE = "\uD83C\uDF4E";

    public Apple(int x, int y) {
        super(x, y);
    }

    public void draw(RetroGameEngine retroGameEngine) {
        retroGameEngine.changeCell(x, y, APPLE);
    }
}
