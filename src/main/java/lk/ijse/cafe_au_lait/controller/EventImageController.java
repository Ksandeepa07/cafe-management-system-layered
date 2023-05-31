package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import lk.ijse.cafe_au_lait.model.EventModel;

public class EventImageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton addImageBtn;

    @FXML
    private Tooltip idToolTip;

    @FXML
    private TextField idTxt;

    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXComboBox<String>eventIdCOmboBox;

    String filePath;


    @FXML
    void CusromerIdKeyTyped(KeyEvent event) {

    }


    @FXML
    void eventIdComboBoxOnAction(ActionEvent event) {

    }

    @FXML
    void addImageBtnClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath();
            System.out.println(filePath);
            // Do something with the selected file
        }

    }

    @FXML
    void saveOnAction(ActionEvent event) throws FileNotFoundException {
        String eventId=eventIdCOmboBox.getValue();
        InputStream in = new FileInputStream(filePath);
        try {
            boolean isSavedIamge= EventModel.saveImage(eventId,in);
            if (isSavedIamge){
                filePath=null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    void loadEventIDs(){
        try {
            ObservableList<String> eventData=EventModel.loadEventIds();
            eventIdCOmboBox.setItems(eventData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        loadEventIDs();

    }

}
