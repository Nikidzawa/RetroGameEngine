package ru.nikidzawa.snakegame;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;

import javafx.scene.paint.Color;
import ru.nikidzawa.snakegame.config.GridPanes;
import ru.nikidzawa.snakegame.config.RetroGameEngine;

public class SnakeGame extends RetroGameEngine {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private boolean isGameStop = false;
    private int score = 0;
    private final static int goal = 300;
    private int frameRate = 300000000;
    private Snake snake;
    private Apple apple;

    public SnakeGame(GridPanes gridPanes) {
        super(gridPanes);
        Platform.runLater(gridPanes::requestFocus);
        gridPanes.setOnKeyPressed(event -> onClick(event.getCode()));
        initialize();
    }

    @Override
    protected void initialize() {
        createGame();
    }

    private void createGame() {
        isGameStop = false;
        snake = new Snake(WIDTH/2, HEIGHT/2);
        setScore(0);
        createNewApple();
        drawGameField();
        animation(frameRate, true);
        showGrid(true);
    }

    @Override
    public void onFrame(AnimationTimer animationTimer) {
        snake.move(apple);
        if (!apple.isAlive) {
            score += 5;
            setScore(score);
            createNewApple();
        }
        if (goal < score) {
            gameWin();
        }
        if (!snake.isAlive) {
            gameOver();
        }
        drawGameField();
    }

    @Override
    public void onClick(KeyCode keyCode) {
        switch (keyCode) {
            case LEFT : snake.setDirection(Direction.LEFT);
                break;
            case RIGHT: snake.setDirection(Direction.RIGHT);
                break;
            case UP: snake.setDirection(Direction.UP);
                break;
            case DOWN: snake.setDirection(Direction.DOWN);
                break;
        }
        if (isGameStop && keyCode == KeyCode.SPACE) {
            createGame();
        }
    }
    private void gameOver () {
        animation(frameRate, false);
        System.out.println("игра окончена");
        isGameStop = true;
    }
    private void gameWin() {
        animation(frameRate, false);
        System.out.println("Поздравляем, вы победили!");
        isGameStop = true;
    }
    public void drawGameField() {
        createGameField(WIDTH, HEIGHT , 30, 30, Color.DARKSEAGREEN, "");
        apple.draw(this);
        snake.draw(this);
    }

    private void createNewApple () {
        do {apple = new Apple((int) (Math.random() * HEIGHT), (int) (Math.random() * WIDTH));}
        while (snake.checkCollision(apple));
    }
    private void setScore (int score) {
        this.score = score;
    }
}