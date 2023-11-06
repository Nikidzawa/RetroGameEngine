package ru.nikidzawa.snakegame.entities;

import ru.nikidzawa.snakegame.config.GameObject;
import ru.nikidzawa.snakegame.config.RetroGameEngine;

public class Apple extends GameObject {
    public boolean isAlive = true;
    private final static String APPLE = "\uD83C\uDF4E";

    public Apple(int x, int y) {
        super(x, y);
    }

    public void draw(RetroGameEngine retroGameEngine) {
        retroGameEngine.changeCell(x, y, APPLE, null, 20);
    }
    public boolean checkCollision (GameObject object) {
        return object.x == this.x && object.y == this.y;
    }
}
