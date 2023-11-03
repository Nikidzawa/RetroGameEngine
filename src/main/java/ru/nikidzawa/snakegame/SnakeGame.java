package ru.nikidzawa.snakegame;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import ru.nikidzawa.snakegame.config.RetroGameEngine;

public class SnakeGame extends RetroGameEngine {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private boolean isGameStop = false;
    private int score = 0;
    private final static int goal = 150;
    private int frameRate = 300000000;
    private Snake snake;
    private Apple apple;

    public SnakeGame(GridPane gridPane) {
        super(gridPane);
        Platform.runLater(gridPane::requestFocus);
        gridPane.setOnKeyPressed(event -> onClick(event.getCode()));
        initialize();
    }

    @Override
    protected void initialize() {
        createGame();
    }

    private void createGame() {
        snake = new Snake(WIDTH/2, HEIGHT/2);
        createNewApple();
        isGameStop = false;
        animation(frameRate, true);
        createGameField(WIDTH, HEIGHT, 30, 30, Color.DARKSEAGREEN);
        showGrid(true);
    }

    @Override
    public void onFrame() {
        snake.move(apple);
        if (!apple.isAlive) {
            score += 5;
            setScore(score);
            createNewApple();
        }
        if (!snake.isAlive) {
            gameOver();
        }
        if (goal == score) {
            gameWin();
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
            createGameField(WIDTH, HEIGHT, 30, 30, Color.DARKSEAGREEN);
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
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                changeCell(x, y, "");
            }
        }
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
    private void setFrameRate (int frameRate) {
        this.frameRate = frameRate;
    }
}