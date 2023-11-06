package ru.nikidzawa.snakegame.config;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ru.nikidzawa.snakegame.SnakeGame;

import java.util.Objects;

public class RetroGameEngine {
    @FXML
    protected Grid grid;
    private AnimationTimer animationTimer;
    public RetroGameEngine(Grid grid) {this.grid = grid;}
    public RetroGameEngine() {}
    @FXML
    protected void initialize() {
        new SnakeGame(grid);
    }
    public void onClick (KeyCode keyCode) {

    }
    public void createGameField(int rowCount, int columnCount, int cellWidth, int cellHeight, Color color) {
        for (int x = 0; x < rowCount; x++) {
            for (int y = 0; y < columnCount; y++) {
                Rectangle rectangle = new Rectangle(cellWidth, cellHeight);
                rectangle.setFill(color);
                Text text = new Text("");
                StackPane stackPane = new StackPane(rectangle, text);
                stackPane.setMaxSize(cellWidth, cellHeight);
                StackPane lastPane = findStackPane(x, y);
                if (lastPane != null) {
                    grid.getChildren().remove(lastPane);
                }
                grid.add(stackPane, x, y);
            }
        }
    }
    private StackPane findStackPane(int x, int y) {
        return (StackPane) grid.getChildren().stream()
                .filter(n -> Objects.equals(Grid.getRowIndex(n), y)
                        && Objects.equals(Grid.getColumnIndex(n), x))
                .findFirst()
                .orElse(null);
    }
    public void changeCell(int x, int y, String text, Color setColorOrNull, int fontSize) {
        StackPane newStackPane = new StackPane();
        StackPane stackPane = findStackPane(x, y);
        Rectangle oldRectangle = null;
        Text oldText = null;

        for (Node node : stackPane.getChildren()) {
            if (node instanceof Rectangle) {
                oldRectangle = (Rectangle) node;
            }
            if (node instanceof Text) {
                oldText = (Text) node;
            }
        }

        if (oldRectangle != null && oldText != null) {
            Rectangle newRectangle = new Rectangle(
                    oldRectangle.getWidth(),
                    oldRectangle.getHeight(),
                    setColorOrNull == null ? oldRectangle.getFill() : setColorOrNull
            );

            Text txt = new Text(text);
            txt.setFill(oldText.getFill());
            txt.setFont(Font.font(fontSize));

            newStackPane.getChildren().addAll(newRectangle, txt);
            grid.getChildren().remove(stackPane);
            grid.add(newStackPane, x, y);
        }
        else {throw new RuntimeException("Ошибка в поиске элементов внутри StackPane");}
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
    public void showGrid (boolean show) {
        grid.setGridLinesVisible(show);
    }
    public int random (int number) {
        return (int) (Math.random() * number);
    }
}
