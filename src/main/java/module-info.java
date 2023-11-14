module ru.nikidzawa.snakegame {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.media;

    requires java.desktop;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    opens ru.nikidzawa.retroGameEngine to javafx.fxml;

    exports ru.nikidzawa.retroGameEngine.config to javafx.fxml;
    opens ru.nikidzawa.retroGameEngine.config;

    exports ru.nikidzawa.retroGameEngine.snakeGame.gameObjects;
    exports ru.nikidzawa.retroGameEngine.snakeGame.service;
    opens ru.nikidzawa.retroGameEngine.snakeGame.service to javafx.fxml;
    exports ru.nikidzawa.retroGameEngine;
    exports ru.nikidzawa.retroGameEngine.controllers;
    opens ru.nikidzawa.retroGameEngine.controllers to javafx.fxml;
    opens ru.nikidzawa.retroGameEngine.snakeGame.gameObjects;
}