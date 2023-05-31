package lk.ijse.cafe_au_lait.util;

import javafx.animation.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;

public class AnimationController {
    private static final int START_TIME = 30;
    private static final SimpleIntegerProperty timeSeconds = new SimpleIntegerProperty();
    private static Timeline timeline;

    public static void fadeAnimation(String fxml, AnchorPane pane) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AnimationController.class.getResource(String.valueOf(fxml)));
        Parent load = fxmlLoader.load();
        pane.getChildren().setAll(load);

        // Fade out old scene
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1));
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.play();

        // Slide in new scene
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.5), load);

        slideIn.setFromY(load.getTranslateX() - 300);
        slideIn.setToY(load.getTranslateX());
        slideIn.play();

        // Remove old scene from parent after fade out is completed
        fadeOut.setOnFinished((event) -> {
            pane.getChildren().remove(0);
        });
    }

    public static void fadeUpAnimation(String fxml, AnchorPane pane) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AnimationController.class.getResource(String.valueOf(fxml)));
        Parent load = fxmlLoader.load();
        pane.getChildren().setAll(load);

        // Fade out old scene
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1));
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.play();

        // Slide in new scene
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.5), load);

        slideIn.setFromY(load.getTranslateX() + 300);
        slideIn.setToY(load.getTranslateX());
        slideIn.play();

        // Remove old scene from parent after fade out is completed
        fadeOut.setOnFinished((event) -> {
            pane.getChildren().remove(0);
        });
    }

    public static void updateTime(Button button, Label label) {
        button.setDisable(true);
        timeSeconds.set(START_TIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(START_TIME + 1),
                new KeyValue(timeSeconds, 0)));
        timeline.setOnFinished(event1 -> {
            button.setDisable(false);
            label.setVisible(false);
        });
        timeline.playFromStart();
        label.textProperty().bind(timeSeconds.asString());


    }


}
