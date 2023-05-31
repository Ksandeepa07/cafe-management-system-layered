package lk.ijse.cafe_au_lait.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LodingPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private ProgressBar pgbar;

    @FXML
    void initialize() {
        assert anchorpane != null : "fx:id=\"anchorpane\" was not injected: check your FXML file 'lodingPage.fxml'.";
        assert pgbar != null : "fx:id=\"pgbar\" was not injected: check your FXML file 'lodingPage.fxml'.";
        new ShowSplashScreen().start();
    }

    class ShowSplashScreen extends Thread {
        public void run() {
            try {
                for (int i = 0; i <= 10; i++) {
                    double x = i * (0.1);
                    pgbar.setProgress(x);


                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Platform.runLater(() -> {
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/view/loginPage.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(LodingPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    anchorpane.getScene().getWindow().hide();
                });
            } catch (Exception ex) {
                Logger.getLogger(LodingPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


}

