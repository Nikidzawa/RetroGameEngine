package ru.nikidzawa.retroGameEngine.snakeGame.service;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;

import javafx.scene.layout.BorderPane;
import ru.nikidzawa.retroGameEngine.config.Color;
import ru.nikidzawa.retroGameEngine.config.Grid;
import ru.nikidzawa.retroGameEngine.config.RetroGameEngine;
import ru.nikidzawa.retroGameEngine.config.SoundAccompaniment;
import ru.nikidzawa.retroGameEngine.snakeGame.gameObjects.Apple;
import ru.nikidzawa.retroGameEngine.snakeGame.gameObjects.Direction;
import ru.nikidzawa.retroGameEngine.snakeGame.gameObjects.Snake;
import ru.nikidzawa.retroGameEngine.snakeGame.gameObjects.Wall;

public class SnakeGame extends RetroGameEngine {

    SoundAccompaniment soundAccompaniment = new SoundAccompaniment();
    public static boolean DIFFICULT;
    public static boolean SOUND;
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private boolean isGameStop = false;
    private int score = 0;
    private final static int goal = 200;
    private final int frameRate = 300000000;
    private Snake snake;
    private Apple apple;
    private Wall wall;


    public SnakeGame(Grid grid, BorderPane pane) {
        super(grid, pane);

        Platform.runLater(grid::requestFocus);
        grid.setOnKeyPressed(event -> onClickKey(event.getCode()));
        initialize();
    }

    @Override
    protected void initialize() {
        createGame();
    }

    private void createGame() {
        if (SOUND) {
            soundAccompaniment.setURL("src/main/java/ru/nikidzawa/retroGameEngine/snakeGame/sounds/");
            soundAccompaniment.startBackGroundMusic("background-music.wav", 0.2F);
        }
        isGameStop = false;
        setScore(0);
        snake = new Snake(WIDTH/2, HEIGHT/2);
        if (DIFFICULT) {
           do {wall = new Wall(random(WIDTH), random(HEIGHT));}
           while (snake.checkCollision(wall));
            animation(frameRate / 2);
        }
        else {animation(frameRate);}
        createNewApple();
        drawGameField();
        createStaticField(Direction.UP, Color.DARKSEAGREEN);
        addTextInField(Direction.UP, "Snake Game", Color.BLACK, 28, 25, 140);
        addTextInField(Direction.UP, "Score: " + score, Color.BLACK, 18, 10, 10 );
        addTextInField(Direction.UP, "Goal: " + goal, Color.BLACK, 18, 10, 369 );
        showGrid(true);
    }

    @Override
    public void onFrame() {
        snake.move(apple, wall);
        if (!apple.isAlive) {
            if (SOUND) {soundAccompaniment.playMusic("coin-collect.wav", 0.8);}
            score += 5;
            setScore(score);
            addTextInField(Direction.UP, "Score: " + score, Color.BLACK, 18, 10, 10 );
            createNewApple();
        }
        if (goal <= score) {
            gameWin();
        }
        if (!snake.isAlive) {
            gameOver();
        }
        drawGameField();
    }

    @Override
    public void onClickKey(KeyCode keyCode) {
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
        if (SOUND)
        {
            soundAccompaniment.stopBackgroundMusic();
            soundAccompaniment.playMusic("lose.wav", 0.7);
        }
        stopAnimation();
        System.out.println("игра окончена");
        isGameStop = true;
    }
    private void gameWin() {
        if (SOUND)
        {
            soundAccompaniment.stopBackgroundMusic();
            soundAccompaniment.playMusic("win.wav", 0.9);
        }
        stopAnimation();
        System.out.println("Поздравляем, вы победили!");
        isGameStop = true;
    }
    public void drawGameField() {
        createGameField(WIDTH, HEIGHT , 30, 30, ru.nikidzawa.retroGameEngine.config.Color.DARKSEAGREEN);
        apple.draw(this);
        snake.draw(this);
        if (DIFFICULT) {
            wall.draw(this);
        }
    }

    private void createNewApple ()
    {
        if (DIFFICULT) {
            do {apple = new Apple(random(HEIGHT), random(WIDTH));}
            while (snake.checkCollision(apple) || wall.checkCollision(apple));
        }
        else {do {apple = new Apple(random(HEIGHT), random(WIDTH));}
            while (snake.checkCollision(apple));}
    }
    private void setScore (int score) {
        this.score = score;
    }
}