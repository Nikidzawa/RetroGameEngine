package ru.nikidzawa.snakegame;

import ru.nikidzawa.snakegame.config.GameObject;
import ru.nikidzawa.snakegame.config.RetroGameEngine;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private static final String HEAD = "\uD83D\uDC7E";
    private static final String BODY = "\u26AB";
    private Direction direction = Direction.LEFT;
    public boolean isAlive = true;

    private List<GameObject> snakeParts = new ArrayList<>();

    public Snake (int x, int y) {
        for (int i = 0; i < 3; i++) {
            GameObject gameObject = new GameObject(x + i, y);
            snakeParts.add(gameObject);
        }
    }

    public void move(Apple apple) {
        GameObject head = createNewHead();
        if (head.x >= SnakeGame.WIDTH || head.y >= SnakeGame.HEIGHT || head.x < 0 || head.y < 0) {
            switch (direction) {
                case UP -> head.y = SnakeGame.HEIGHT - 1;
                case DOWN -> head.y = 0;
                case LEFT -> head.x = SnakeGame.WIDTH - 1;
                case RIGHT -> head.x = 0;
            };
        }
        if (checkCollision(head)) {
            isAlive = false;
            return;
        }
        if (checkCollision(apple)){
            apple.isAlive = false;

        }
        else {
            removeTail();
        }
        snakeParts.add(0, head);
    }

    public GameObject createNewHead() {
        GameObject head = snakeParts.get(0);
        GameObject newHead;
        return switch (direction) {
            case UP -> newHead = new GameObject(head.x, head.y - 1);
            case DOWN -> newHead = new GameObject(head.x, head.y + 1);
            case LEFT -> newHead = new GameObject(head.x - 1, head.y);
            case RIGHT -> newHead = new GameObject(head.x + 1, head.y);
        };
    }

    private void removeTail () {
        snakeParts.remove(snakeParts.size() - 1);
    }
    public void draw (RetroGameEngine game) {
        for (int i = 0; i < snakeParts.size(); i++) {
            GameObject part = snakeParts.get(i);
            if (i == 0) {
                game.changeCell(part.x, part.y, HEAD, null, 22);
            }
            else {
                game.changeCell(part.x, part.y, BODY, null, 22);
            }
        }
    }
    public boolean checkCollision (GameObject object) {
        for (int i = 0; i < snakeParts.size(); i++) {
            GameObject part = snakeParts.get(i);
            if (part.x == object.x && part.y == object.y)
                return true;
        }
        return false;
    }


    public void setDirection (Direction direction) {
        if ((this.direction == Direction.LEFT || this.direction == Direction.RIGHT) && snakeParts.get(0).x == snakeParts.get(1).x) {
            return;
        }
        if ((this.direction == Direction.UP || this.direction == Direction.DOWN) && snakeParts.get(0).y == snakeParts.get(1).y) {
            return;
        }
        if (direction == Direction.UP && this.direction == Direction.DOWN) {
            return;
        } else if (direction == Direction.LEFT && this.direction == Direction.RIGHT) {
            return;
        } else if (direction == Direction.RIGHT && this.direction == Direction.LEFT) {
            return;
        } else if (direction == Direction.DOWN && this.direction == Direction.UP) {
            return;
        }
        this.direction = direction;
    }
}
