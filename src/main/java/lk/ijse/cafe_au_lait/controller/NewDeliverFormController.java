package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe_au_lait.bo.BOFactory;
import lk.ijse.cafe_au_lait.bo.custom.PlaceOrderBO;
import lk.ijse.cafe_au_lait.bo.custom.impl.PlaceOrderBOImpl;
import lk.ijse.cafe_au_lait.dto.DeliveryDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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

    PlaceOrderBO placeOrderBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.PLACE_ORDER);


    @FXML
    void saveBtnClick(ActionEvent event) {
        String deliverId = deliverIdTxt.getText();
        String orderId = orderIdLbl.getText();
        String empId = String.valueOf(empIdCOmbo.getValue());
        String location = locationTxt.getText();

        DeliveryDTO newDeliverDto = new DeliveryDTO(deliverId, location, orderId, empId);


        try {
//            PlaceOrderModel.sendObject(newDeliverDto);
//            System.out.println(newDeliverDto.getDeliverId());
          placeOrderBO.saveDelivery(newDeliverDto);


        } catch (Exception throwables) {
            System.out.println(throwables);

        }

        ancPane.getScene().getWindow().hide();
    }


    private void generateNextOrderId() {
        try {
            String id = placeOrderBO.generateNextOrderId();
            orderIdLbl.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    void loadEmployeeId() {

        ArrayList<String> employeeData = null;
        try {
            employeeData = placeOrderBO.loadEmpIds();
            ObservableList<String> obList= FXCollections.observableArrayList();
            for (String employeeDatum : employeeData) {
                obList.add(employeeDatum);
            }
            empIdCOmbo.setItems(obList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }


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
