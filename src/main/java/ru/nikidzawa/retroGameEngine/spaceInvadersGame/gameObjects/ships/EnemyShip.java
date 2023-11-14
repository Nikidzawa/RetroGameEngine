package ru.nikidzawa.retroGameEngine.spaceInvadersGame.gameObjects.ships;

import ru.nikidzawa.retroGameEngine.snakeGame.gameObjects.Direction;
import ru.nikidzawa.retroGameEngine.spaceInvadersGame.visualization.PatternMatrix;
import ru.nikidzawa.retroGameEngine.spaceInvadersGame.gameObjects.Bullet;

public class EnemyShip extends Ship {
    public EnemyShip(double x, double y) {
        super(x, y);
        setFrame(PatternMatrix.ENEMY);
    }
    @Override
    public Bullet fire() {
        return (new Bullet (x + 1, y + height, Direction.DOWN));
    }

    public void move (Direction direction, double speed) {
        switch (direction) {
            case RIGHT -> x += speed;
            case LEFT -> x -= speed;
            case DOWN -> y += 2;
        }
    }
    @Override
    public void kill() {
        if (!isAlive) {
            return;
        }
        isAlive = false;

        this.setAnimatedView(false,
                PatternMatrix.KILL_ENEMY_ANIMATION_FIRST,
                PatternMatrix.KILL_ENEMY_ANIMATION_SECOND,
                PatternMatrix.KILL_ENEMY_ANIMATION_THIRD);
    }
}
