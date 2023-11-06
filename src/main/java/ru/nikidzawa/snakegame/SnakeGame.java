package ru.nikidzawa.snakegame;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;

import javafx.scene.paint.Color;
import ru.nikidzawa.snakegame.config.Grid;
import ru.nikidzawa.snakegame.config.RetroGameEngine;
import ru.nikidzawa.snakegame.config.SoundAccompaniment;
import ru.nikidzawa.snakegame.entities.Apple;
import ru.nikidzawa.snakegame.entities.Direction;
import ru.nikidzawa.snakegame.entities.Snake;
import ru.nikidzawa.snakegame.entities.Wall;

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


    public SnakeGame(Grid grid) {
        super(grid);

        Platform.runLater(grid::requestFocus);
        grid.setOnKeyPressed(event -> onClick(event.getCode()));
        initialize();
    }

    @Override
    protected void initialize() {
        createGame();
    }

    private void createGame() {
        if (SOUND) {
            soundAccompaniment.setURL("src/main/java/ru/nikidzawa/snakegame/sounds/");
            soundAccompaniment.startBackGroundMusic("background-music.wav", 0.2F);
        }
        isGameStop = false;
        setScore(0);
        snake = new Snake(WIDTH/2, HEIGHT/2);
        if (DIFFICULT) {
           do {wall = new Wall(random(WIDTH), random(HEIGHT));}
           while (snake.checkCollision(wall));
            animation(frameRate / 2, true);
        }
        else {animation(frameRate, true);}
        createNewApple();
        drawGameField();
        showGrid(true);
    }

    @Override
    public void onFrame() {
        snake.move(apple, wall);
        if (!apple.isAlive) {
            if (SOUND) {soundAccompaniment.playMusic("coin-collect.wav", 0.8);}
            score += 5;
            setScore(score);
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
        if (SOUND)
        {
            soundAccompaniment.stopBackgroundMusic();
            soundAccompaniment.playMusic("lose.wav", 0.7);
        }
        animation(frameRate, false);
        System.out.println("игра окончена");
        isGameStop = true;
    }
    private void gameWin() {
        if (SOUND)
        {
            soundAccompaniment.stopBackgroundMusic();
            soundAccompaniment.playMusic("win.wav", 0.9);
        }
        animation(frameRate, false);
        System.out.println("Поздравляем, вы победили!");
        isGameStop = true;
    }
    public void drawGameField() {
        createGameField(WIDTH, HEIGHT , 30, 30, Color.DARKSEAGREEN);
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