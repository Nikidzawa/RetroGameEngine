package ru.nikidzawa.retroGameEngine.spaceInvadersGame;

import ru.nikidzawa.retroGameEngine.snakeGame.entities.Direction;

import java.util.List;

public class Player extends Ship{
    public boolean isAlive = true;
    private Direction direction = Direction.UP;
    public Player() {
        super(32, 32);
        setFrame(PatternMatrix.PLAYER);
    }
    @Override
    public Bullet fire() {
        if (!isAlive) {
            return null;
        }
        return new Bullet(x + 2, y - PatternMatrix.BULLET.length, Direction.UP);
    }

    public void move () {
        if (!isAlive) {
            return;
        }

        int playerMoveX = 0;
        if (direction == Direction.LEFT) {
            playerMoveX = -1;
        }
        else if (direction == Direction.RIGHT) {
            playerMoveX = 1;
        }
        x += playerMoveX;
        if (x < 0) {
            x = 0;
        }
        if (x + width > SpaceInvadersGame.WIDTH) {
            x = SpaceInvadersGame.WIDTH - width;
        }
    }
    public void setDirection (Direction newDirection) {
        if (newDirection != Direction.DOWN) {
            this.direction = newDirection;
        }
    }
    public Direction getDirection () {
        return direction;
    }

    public void verifyHit(List<Bullet> bullets) {
        if (bullets.isEmpty()) {
            return;
        }

        for (Bullet bullet : bullets) {
            if (isAlive && bullet.isAlive && isCollision(bullet)) {
                kill();
                bullet.kill();
                break;
            }
        }
    }

    @Override
    public void kill() {
        if (!isAlive) {
            return;
        }
        isAlive = false;

        super.setAnimatedView(false,
                PatternMatrix.KILL_PLAYER_ANIMATION_FIRST,
                PatternMatrix.KILL_PLAYER_ANIMATION_SECOND,
                PatternMatrix.KILL_PLAYER_ANIMATION_THIRD,
                PatternMatrix.DEAD_PLAYER);
    }

    public void win() {
        this.setFrame(PatternMatrix.WIN_PLAYER);
    }
}
