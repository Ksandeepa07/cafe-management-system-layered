package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe_au_lait.dto.Delivery;
import lk.ijse.cafe_au_lait.model.EmployeeModel;
import lk.ijse.cafe_au_lait.model.OrderModel;
import lk.ijse.cafe_au_lait.model.PlaceOrderModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewDeliverFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField deliverIdTxt;

    @FXML
    private JFXComboBox<String> empIdCOmbo;


    @FXML
    private TextField locationTxt;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private TextField orderIdLbl;


    @FXML
    void saveBtnClick(ActionEvent event) {
        String deliverId = deliverIdTxt.getText();
        String orderId = orderIdLbl.getText();
        String empId = String.valueOf(empIdCOmbo.getValue());
        String location = locationTxt.getText();

        Delivery newDeliverDto = new Delivery(deliverId, location, orderId, empId);


        try {
            PlaceOrderModel.sendObject(newDeliverDto);

        } catch (Exception throwables) {

        }

        ancPane.getScene().getWindow().hide();
    }


    private void generateNextOrderId() {
        try {
            String id = OrderModel.getNextOrderId();
            orderIdLbl.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    void loadEmployeeId() {

        ObservableList<String> eventData = null;
        try {
            eventData = EmployeeModel.loadEmpIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        empIdCOmbo.setItems(eventData);

    }


    @FXML
    void initialize() {
        loadEmployeeId();
        generateNextOrderId();
        assert deliverIdTxt != null : "fx:id=\"deliverIdTxt\" was not injected: check your FXML file 'newDeliverForm.fxml'.";
        assert empIdCOmbo != null : "fx:id=\"empIdCOmbo\" was not injected: check your FXML file 'newDeliverForm.fxml'.";
        assert locationTxt != null : "fx:id=\"locationTxt\" was not injected: check your FXML file 'newDeliverForm.fxml'.";
        assert orderIdLbl != null : "fx:id=\"orderIdLbl\" was not injected: check your FXML file 'newDeliverForm.fxml'.";

    }

}
