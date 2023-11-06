module ru.nikidzawa.snakegame {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.media;

    requires java.desktop;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    opens ru.nikidzawa.snakegame to javafx.fxml;

    exports ru.nikidzawa.snakegame.config to javafx.fxml;
    opens ru.nikidzawa.snakegame.config;

    exports ru.nikidzawa.snakegame;
    exports ru.nikidzawa.snakegame.entities;
    opens ru.nikidzawa.snakegame.entities to javafx.fxml;
}