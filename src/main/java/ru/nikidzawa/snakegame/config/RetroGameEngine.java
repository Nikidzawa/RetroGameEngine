package ru.nikidzawa.snakegame.config;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ru.nikidzawa.snakegame.SnakeGame;

import java.util.Objects;

public class RetroGameEngine {
    @FXML
    protected GridPane gridPane;
    private AnimationTimer animationTimer;
    public RetroGameEngine(GridPane gridPane) {this.gridPane = gridPane;}
    public RetroGameEngine() {}
    @FXML
    protected void initialize() {
        new SnakeGame(gridPane);
    }
    public void onClick (KeyCode keyCode) {

    }

    public void createGameField(int rowCount, int columnCount, int cellWidth, int cellHeight, Color color) {
        gridPane.setGridLinesVisible(true);
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                Rectangle rectangle = new Rectangle(cellWidth, cellHeight);
                rectangle.setFill(color);

                gridPane.add(rectangle, i, j);
            }
        }
    }
    public void changeCellColor(int x, int y, Color color) {
        Rectangle rectangle = findRectangle(x, y);
        rectangle.setFill(color);
    }
    public Rectangle findRectangle(int x, int y) {
        return (Rectangle) gridPane.getChildren().stream()
                .filter(n -> Objects.equals(GridPane.getRowIndex(n), x)
                        && Objects.equals(GridPane.getColumnIndex(n), y))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Не удалось найти указаную ячейку. Проверьте что вы ранее создали игровое поле"));
    }
    public void changeCell(int x, int y, String smile) {
        Rectangle previousNode = findRectangle(x, y);
        StackPane stackPane = new StackPane();
        Rectangle rectangle = new Rectangle(previousNode.getHeight(), previousNode.getWidth());
        rectangle.setFill(previousNode.getFill());

        Text text = new Text(smile);
        text.setFont(Font.font(22));
        stackPane.getChildren().addAll(rectangle, text);
        gridPane.getChildren().remove(rectangle);
        gridPane.add(stackPane, x, y);
    }
    public void animation(int i, boolean startAnimation) {
        if (startAnimation) {
            animationTimer = new AnimationTimer() {
                long lastTime = 0;
                @Override
                public void handle(long now) {
                    if (now - lastTime >= i) {
                        onFrame();
                        lastTime = now;
                    }
                }
            };animationTimer.start();
        }
        else {
            if (animationTimer != null) {
                animationTimer.stop();
            }
        }
    }
    public void onFrame () {}


    public void showGrid (boolean show) {gridPane.setGridLinesVisible(show);}
    public boolean isShowGrid () {return gridPane.isGridLinesVisible();}
}
