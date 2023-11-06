package ru.nikidzawa.snakegame.config;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ru.nikidzawa.snakegame.SnakeGame;
import ru.nikidzawa.snakegame.entities.Direction;

import java.util.Objects;

public class RetroGameEngine {
    @FXML
    protected Grid grid;
    @FXML
    private BorderPane pane;

    private AnimationTimer animationTimer;
    public RetroGameEngine(Grid grid, BorderPane pane) {this.grid = grid; this.pane = pane;}
    public RetroGameEngine() {}
    @FXML
    protected void initialize() {
        new SnakeGame(grid, pane);
    }
    public void onClick (KeyCode keyCode) {}
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
    public void createStaticField (Direction direction, Color backGroundColor) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(60);
        anchorPane.setStyle("-fx-background-color: " + colorToHex(backGroundColor));
        switch (direction) {
            case UP : pane.setTop(anchorPane);
            break;
            case LEFT: pane.setLeft(anchorPane);
            break;
            case RIGHT: pane.setRight(anchorPane);
            break;
            case DOWN: pane.setBottom(anchorPane);
            break;
        }
    }
    public void addTextInField(Direction direction, String message, Color textColor,
                               int fontSize, double indentTop, double indentLeft)
    {
        AnchorPane anchorPane = findAnchorPane(direction);
        if (anchorPane != null) {
            Text text = new Text(message);
            text.setFill(textColor);
            text.setFont(Font.font("Arial", fontSize));
            Text lastText = checkText(text, anchorPane);
            if (lastText != null) {
                anchorPane.getChildren().remove(lastText);
            }
            anchorPane.getChildren().add(text);
            AnchorPane.setTopAnchor(text, indentTop);
            AnchorPane.setLeftAnchor(text, indentLeft);
        }
    }
    private Text checkText (Text text, AnchorPane anchorPane) {
        return (Text) anchorPane.getChildren().stream()
                .filter(node -> node instanceof Text && (
                        (Text) node).getText().replaceAll("\\d", "")
                        .equals(text.getText().replaceAll("\\d", "")))
                .findFirst()
                .orElse(null);
    }

    private AnchorPane findAnchorPane (Direction direction) {
        AnchorPane anchorPane;
        switch (direction) {
            case UP : anchorPane = (AnchorPane) pane.getTop();
                return anchorPane;
            case LEFT: anchorPane = (AnchorPane) pane.getLeft();
                return anchorPane;
            case RIGHT: anchorPane = (AnchorPane) pane.getRight();
                return anchorPane;
            case DOWN: anchorPane = (AnchorPane) pane.getBottom();
                return anchorPane;
        }
        return null;
    }
    private String colorToHex(Color color) {
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);

        return String.format("#%02X%02X%02X", red, green, blue);
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
    public void animation(int speed) {
        animationTimer = new AnimationTimer() {
            long lastTime = 0;
            @Override
            public void handle(long now) {
                if (now - lastTime >= speed) {
                    onFrame();
                    lastTime = now;
                }
            }
        };animationTimer.start();
    }
    public void stopAnimation () {
        animationTimer.stop();
    }
    public void onFrame () {}
    public void showGrid (boolean show) {
        grid.setGridLinesVisible(show);
    }
    public int random (int number) {
        return (int) (Math.random() * number);
    }
}
