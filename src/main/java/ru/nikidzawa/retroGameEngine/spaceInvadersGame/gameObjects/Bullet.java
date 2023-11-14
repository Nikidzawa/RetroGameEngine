package ru.nikidzawa.retroGameEngine.spaceInvadersGame.gameObjects;

import ru.nikidzawa.retroGameEngine.snakeGame.gameObjects.Direction;
import ru.nikidzawa.retroGameEngine.spaceInvadersGame.visualization.PatternMatrix;

public class Bullet extends GameObject {
    private int directionBullet;
    public boolean isAlive = true;
    public Bullet(double x, double y, Direction direction) {
        super(x, y);
        setMatrix(PatternMatrix.BULLET);
        if (direction == Direction.UP) {
            directionBullet = -1;
        }
        else {
            directionBullet = 1;
        }
    }
    public void move () {
        y += directionBullet;
    }
    public void kill() {
        isAlive = false;
    }
}
