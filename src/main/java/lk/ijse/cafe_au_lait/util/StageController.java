package lk.ijse.cafe_au_lait.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StageController {
    public static void changeStage(String fxml, String title) {
        FXMLLoader fxmlLoader = new FXMLLoader(StageController.class.getResource(String.valueOf(fxml)));
        Parent root1 = null;
        try {
            root1 = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }

    public static void changeScene(String fxml, AnchorPane pane) {
        Parent load = null;
        try {
            load = FXMLLoader.load(StageController.class.getResource(String.valueOf(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(load);


    }
}
