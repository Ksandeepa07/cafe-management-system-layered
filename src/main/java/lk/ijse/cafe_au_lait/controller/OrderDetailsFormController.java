package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailsFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private JFXButton checkDeliveryDetailsBtn;

    @FXML
    private TableColumn<?, ?> coiDeliveryId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colEmployeId;

    @FXML
    private TableColumn<?, ?> colLocattion;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TextField searchDeliveryId;

    @FXML
    private TextField searchEmpId;

    @FXML
    private ImageView searchIcon;

    @FXML
    private ImageView searchIcon1;

    @FXML
    private ImageView searchIcon11;

    @FXML
    private TextField searchIdTxt;

    @FXML
    private TableView<?> tblDelivery;

    @FXML
    void checkdeliveryDetailsBtnCllick(ActionEvent event) {

    }

    @FXML
    void searchIconClick(MouseEvent event) {

    }

    @FXML
    void searchTable(KeyEvent event) {

    }

    @FXML
    void searchTableByDeliveryId(KeyEvent event) {

    }

    @FXML
    void searchTableByEmpId(KeyEvent event) {

    }

    @FXML
    void tblClick(MouseEvent event) {

    }

    @FXML
    void initialize() {


    }

}
