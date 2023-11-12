package ru.nikidzawa.retroGameEngine.spaceInvadersGame;

import ru.nikidzawa.retroGameEngine.config.RetroGameEngine;
import ru.nikidzawa.retroGameEngine.snakeGame.entities.Direction;

import java.util.ArrayList;
import java.util.List;

public class EnemyFleet {
    private static final int ROW_COUNT = 3;
    private static final int COLUMN_COUNT = 10;
    private static final int STEP = PatternMatrix.ENEMY.length + 1;
    private static List<EnemyShip> ships;
    private Direction direction = Direction.RIGHT;
    public EnemyFleet () {
        createShips();
    }
    private void createShips () {
        ships = new ArrayList<>();
        for (int x = 0; x < ROW_COUNT; x++) {
            for (int y = 0; y < COLUMN_COUNT; y++) {
                ships.add(new EnemyShip(y * STEP + 12, x * STEP));
            }
        }
    }
    public void draw (RetroGameEngine retroGameEngine) {
        for (GameObject ship : ships) {
            ship.draw(retroGameEngine);
        }
    }
    public int countShips () {
        return ships.size();
    }

    public void move() {
        if (countShips() == 0) {
            return;
        }

        Direction currentDirection = direction;
        if (direction == Direction.LEFT && getLeftBorder() < 0) {
            direction = Direction.RIGHT;
            currentDirection = Direction.DOWN;
        }
        else if (direction == Direction.RIGHT && getRightBorder() > SpaceInvadersGame.WIDTH) {
            direction = Direction.LEFT;
            currentDirection = Direction.DOWN;
        }
        double speed = getSpeed();
        for (EnemyShip ship : ships) {
            ship.move(currentDirection, speed);
        }
    }

    public Bullet fire(RetroGameEngine retroGameEngine) {
        if (ships.isEmpty()) {
            return null;
        }
    int count = retroGameEngine.random(100 / SpaceInvadersGame.COMPLEXITY);
    if (count > 0) {
        return null;
    }

    int random = retroGameEngine.random(ships.size());
    EnemyShip ship = ships.get(random);

    return ship.fire();
    }
    public double getBottomBorder() {
        double bottom = 0;
        for (GameObject ship : ships) {
            if (ship.y + ship.height > bottom) {
                bottom = ship.y + ship.height;
            }
        }
        return bottom;
    }

    private double getLeftBorder() {
        double left = SpaceInvadersGame.WIDTH;
        for (GameObject ship : ships) {
            if (ship.x < left) {
                left = ship.x;
            }
        }
        return left;
    }

    private double getRightBorder () {
        double right = 0;
        for (GameObject ship : ships) {
            if (ship.x + ship.width > right) {
                right = ship.x + ship.width;
            }
        }
        return right;
    }
    private double getSpeed () {
        int count = ships.size();
        double speed = 3. / count;
        return speed > 2. ? 2. : speed;
    }
    public void deleteHiddenShips() {
        for (EnemyShip ship : new ArrayList<>(ships)) {
            if (!ship.isVisible()) {
                ships.remove(ship);
            }
        }
    }

    public void verifyHit(List<Bullet> bullets) {
        if (bullets.isEmpty()) {
            return;
        }

        for (Bullet bullet : bullets) {
            for (EnemyShip ship : ships) {
                if (ship.isAlive && bullet.isAlive && ship.isCollision(bullet)) {
                    ship.kill();
                    bullet.kill();
                }
            }
        }
    }
}
