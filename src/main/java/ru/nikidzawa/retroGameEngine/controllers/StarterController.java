package ru.nikidzawa.retroGameEngine.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;
import ru.nikidzawa.retroGameEngine.config.Games;
import ru.nikidzawa.retroGameEngine.config.RetroGameEngine;

public class StarterController {
        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button button;

        @FXML
        private SplitMenuButton menu;

        @FXML
        private MenuItem snake;

        @FXML
        private MenuItem spaceInv;

        @FXML
        void initialize() {
            snake.setOnAction(event -> RetroGameEngine.GAME = Games.SNAKE);
            spaceInv.setOnAction(event -> RetroGameEngine.GAME = Games.SPACE_INVADERS);
            button.setOnAction(event -> {
                    button.getScene().getWindow().hide();
                    FXMLLoader loader = null;
                    if (RetroGameEngine.GAME == Games.SNAKE) {
                            loader = new FXMLLoader(getClass().getResource("snake-game-menu.fxml"));
                    }
                    else if (RetroGameEngine.GAME == Games.SPACE_INVADERS) {
                            loader = new FXMLLoader(getClass().getResource("space-invaders-menu.fxml"));
                    }
                    try {
                            loader.load();
                    } catch (IOException e) {
                            throw new RuntimeException(e);
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Snake Menu");
                    stage.show();
            });

        }

}
