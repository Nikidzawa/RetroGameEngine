package ru.nikidzawa.retroGameEngine;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import ru.nikidzawa.retroGameEngine.snakeGame.service.SnakeGame;

public class StarterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button;

    @FXML
    private CheckBox difficult;

    @FXML
    private CheckBox sound;

    @FXML
    void initialize() {
        button.setOnAction(event -> {
            SnakeGame.DIFFICULT = difficult.isSelected();
            SnakeGame.SOUND = sound.isSelected();

            button.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("snake-game.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Snake Game");
            stage.showAndWait();
        });
    }
}
