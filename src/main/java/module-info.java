module ru.nikidzawa.snakegame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    opens ru.nikidzawa.snakegame to javafx.fxml;

    exports ru.nikidzawa.snakegame.config to javafx.fxml;
    opens ru.nikidzawa.snakegame.config;

    exports ru.nikidzawa.snakegame;
}