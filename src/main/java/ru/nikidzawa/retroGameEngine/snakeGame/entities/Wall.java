package ru.nikidzawa.retroGameEngine.snakeGame.entities;

import ru.nikidzawa.retroGameEngine.config.Color;
import ru.nikidzawa.retroGameEngine.snakeGame.service.SnakeGame;
import ru.nikidzawa.retroGameEngine.snakeGame.GameObject;
import ru.nikidzawa.retroGameEngine.config.RetroGameEngine;

import java.util.ArrayList;
import java.util.List;

public class Wall extends GameObject {
    public List<GameObject> walls = new ArrayList<>();
    private static final String WALL = "⚠";
    public Wall(int x, int y) {
        super(x, y);
        for (int i = 0; i < SnakeGame.HEIGHT/1.5; i++) {
            GameObject gameObject = new GameObject((int) (Math.random() * SnakeGame.WIDTH),
                    (int) (Math.random() * SnakeGame.HEIGHT));
            walls.add(gameObject);
        }
    }
    public void draw(RetroGameEngine retroGameEngine) {
        for (GameObject gameObject : walls) {
            retroGameEngine.changeCell(gameObject.x, gameObject.y, WALL, Color.NONE, 22, Color.BLACK);
        }
    }
    public boolean checkCollision (GameObject object) {
        for (GameObject gameObject : walls) {
            if (gameObject.x == object.x && gameObject.y == object.y)
                return true;
        }
        return false;
    }
}
