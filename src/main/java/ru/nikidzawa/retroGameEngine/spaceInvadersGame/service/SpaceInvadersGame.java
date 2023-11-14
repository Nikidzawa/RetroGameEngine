package ru.nikidzawa.retroGameEngine.spaceInvadersGame.service;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import ru.nikidzawa.retroGameEngine.config.Color;
import ru.nikidzawa.retroGameEngine.config.Grid;
import ru.nikidzawa.retroGameEngine.config.RetroGameEngine;
import ru.nikidzawa.retroGameEngine.snakeGame.gameObjects.Direction;
import ru.nikidzawa.retroGameEngine.spaceInvadersGame.gameObjects.Bullet;
import ru.nikidzawa.retroGameEngine.spaceInvadersGame.gameObjects.Star;
import ru.nikidzawa.retroGameEngine.spaceInvadersGame.gameObjects.ships.EnemyFleet;
import ru.nikidzawa.retroGameEngine.spaceInvadersGame.gameObjects.ships.Player;

import java.util.ArrayList;
import java.util.List;


public class SpaceInvadersGame extends RetroGameEngine {
    public SpaceInvadersGame (Grid grid, BorderPane pane) {
        super(grid, pane);
        Platform.runLater(grid::requestFocus);
        grid.setOnKeyPressed(event -> onClickKey(event.getCode()));
        grid.setOnKeyReleased(event -> onKeyReleased(event.getCode()));
        initialize();
    }
    public static final int HEIGHT = 64;
    public static final int WIDTH = 64;
    private List<Star> stars;
    private  List<Bullet> enemyBullets;
    private List<Bullet> playerBullets;
    private EnemyFleet enemyFleet;
    private Player player;
    public static final int COMPLEXITY = 5;
    public static final int  PLAYER_BULLETS_MAX = 2;
    public boolean isGameStopped = false;
    private int animationsCount;


    @Override
    protected void initialize() {
        createGame();
    }

    private void createGame() {
        isGameStopped = false;
        animationsCount = 0;
        createStars();
        enemyFleet = new EnemyFleet();
        enemyBullets = new ArrayList<>();
        playerBullets = new ArrayList<>();
        player = new Player();
        createStars();
        drawField();
        animation(300);
    }
    private void drawField () {
        createGameField(WIDTH, HEIGHT, 15, 15, Color.BLACK);
        for (Star star : stars) {
            star.draw(this);
        }
        player.draw(this);
        enemyFleet.draw(this);

        for (Bullet bullet : enemyBullets) {
            bullet.draw(this);
        }
        for (Bullet bullet : playerBullets) {
            bullet.draw(this);
        }
    }

    private void moveSpaceObjects () {
        enemyFleet.move();
        player.move();
        for (Bullet enemyBullet : enemyBullets) {
            enemyBullet.move();
        }
        for (Bullet playerBullet : playerBullets) {
            playerBullet.move();
        }
    }

    private void check () {
        player.verifyHit(enemyBullets);
        enemyFleet.verifyHit(playerBullets);
        enemyFleet.deleteHiddenShips();
        removeDeadBullets();

        if (!player.isAlive) {
            stopGameWithDelay();
        }

        if (enemyFleet.getBottomBorder() >= player.y) {
            player.kill();
        }

        if (enemyFleet.countShips() == 0) {
            player.win();
            stopGameWithDelay();
        }
    }
    public void onKeyReleased(KeyCode keyCode) {
        if (KeyCode.LEFT == keyCode && player.getDirection() == Direction.LEFT) {
            player.setDirection(Direction.UP);
        }
        if (KeyCode.RIGHT == keyCode && player.getDirection() == Direction.RIGHT) {
            player.setDirection(Direction.UP);
        }
    }

    @Override
    public void onClickKey(KeyCode keyCode) {
        switch (keyCode) {
            case SPACE : {
                Bullet bullet = player.fire();
                if (bullet != null && playerBullets.size() < PLAYER_BULLETS_MAX) {
                    playerBullets.add(bullet);
                }
                break;
            }
            case LEFT:
                player.setDirection(Direction.LEFT);
                break;

            case RIGHT:
                player.setDirection(Direction.RIGHT);
                break;
        }
    }

    @Override
    public void onFrame() {
        moveSpaceObjects();
        check();
        Bullet bullet = enemyFleet.fire(this);
        if (bullet != null) {
            enemyBullets.add(bullet);
        }
        drawField();
    }

    private void createStars () {
        stars = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            stars.add(new Star(random(WIDTH), random(HEIGHT)));
        }
    }

    private void removeDeadBullets() {
        for (Bullet bullet : new ArrayList<>(enemyBullets)) {
            if (!bullet.isAlive || bullet.y >= HEIGHT - 1) {
                enemyBullets.remove(bullet);
            }
        }
        for (Bullet bullet : new ArrayList<>(playerBullets)) {
            if (!bullet.isAlive || bullet.y + bullet.height < 0) {
                playerBullets.remove(bullet);
            }
        }
    }
    private void stopGame(boolean isWin) {
        isGameStopped = true;
        stopAnimation();

        if (isWin) {
            System.out.println("Победа");
        } else {
            System.out.println("Поражение");
        }
    }

    private void stopGameWithDelay() {
        animationsCount++;
        if (animationsCount >= 10) {
            stopGame(player.isAlive);
        }
    }
}
