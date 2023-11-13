package ru.nikidzawa.retroGameEngine.snakeGame.entities;

import ru.nikidzawa.retroGameEngine.config.Color;
import ru.nikidzawa.retroGameEngine.snakeGame.GameObject;
import ru.nikidzawa.retroGameEngine.config.RetroGameEngine;

public class Apple extends GameObject {
    public boolean isAlive = true;
    private final static String APPLE = "\uD83C\uDF4E";

    public Apple(int x, int y) {
        super(x, y);
    }

    public void draw(RetroGameEngine retroGameEngine) {
        retroGameEngine.changeCell(x, y, APPLE, Color.NONE, 20, Color.BLACK);
    }
}
