package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import lk.ijse.cafe_au_lait.bo.BOFactory;
import lk.ijse.cafe_au_lait.bo.custom.EventBO;
import lk.ijse.cafe_au_lait.bo.custom.EventImageBO;
import lk.ijse.cafe_au_lait.dto.EventDTO;
import lk.ijse.cafe_au_lait.dto.EventImageDTO;
import lk.ijse.cafe_au_lait.entity.Event;
import lk.ijse.cafe_au_lait.entity.EventImage;

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
    private AnchorPane eventImageAncPane;
    @FXML
    private JFXComboBox<String>eventIdCOmboBox;

    String filePath;

    EventImageBO eventImageBO=BOFactory.getInstance().getBO(BOFactory.BOTypes.EVENTIMAGE);


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
            EventImageDTO eventImageDTO=new EventImageDTO(eventId,in);
            boolean isSavedIamge= eventImageBO.saveImage(eventImageDTO);
            if (isSavedIamge){
                filePath=null;
                eventImageAncPane.getScene().getWindow().hide();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    void loadEventIDs(){
        try {
            ArrayList<String> eventData=eventImageBO.loadEventIds();
            ObservableList<String> obList= FXCollections.observableArrayList();
            for (String eventDatum : eventData) {
                obList.add(eventDatum);
            }
            eventIdCOmboBox.setItems(obList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        loadEventIDs();

    }

}
