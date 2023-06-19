package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe_au_lait.controller.util.StageController;
import lk.ijse.cafe_au_lait.controller.util.TimeController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CashierDashboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXButton homeBtn;

    @FXML
    private JFXButton inventoryBtn;

    @FXML
    private JFXButton deliveryBtn;

    @FXML
    private JFXButton customerBtn;

    @FXML
    private JFXButton ordersBtn;

    @FXML
    private JFXButton eventBtn;

    @FXML
    private Label timeLbl;

    @FXML
    private Label datLbl;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private JFXButton supplyLoadBtn;


    @FXML
    void customerBtnClick(ActionEvent event) {
        StageController.changeScene("/view/cashierCustomer.fxml", ancPane);


    }

    @FXML
    void deliveryBtnClick(ActionEvent event) {
        StageController.changeScene("/view/deliveryDetailsForm.fxml", ancPane);

    }

    @FXML
    void eventBttnClick(ActionEvent event) throws IOException {
        StageController.changeScene("/view/cashierEvent.fxml", ancPane);

    }

    @FXML
    void homeBtnClick(ActionEvent event) {
        StageController.changeScene("/view/cashierHomeForm.fxml", ancPane);

    }

    @FXML
    void inventoryBtnClick(ActionEvent event) {

        StageController.changeScene("/view/cashierInventory.fxml", ancPane);


    }

    @FXML
    void supplyBtnClick(ActionEvent event) {
        StageController.changeScene("/view/SupplierLoadForm.fxml", ancPane);


    }


    @FXML
    void logoutClick(MouseEvent event) {
        anchorpane.getScene().getWindow().hide();
        StageController.changeStage("/view/loginPage.fxml", "Login");

    }

    @FXML
    void ordersBtnClick(ActionEvent event) {
        StageController.changeScene("/view/cashierOrdeForm.fxml", ancPane);

    }

    @FXML
    void initialize() {
        TimeController.timeNow(timeLbl, datLbl);

    }
}
