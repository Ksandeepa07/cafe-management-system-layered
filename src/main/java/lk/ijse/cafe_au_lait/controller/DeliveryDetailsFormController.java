package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe_au_lait.bo.BOFactory;
import lk.ijse.cafe_au_lait.bo.custom.DeliveryBO;
import lk.ijse.cafe_au_lait.dto.DeliveryDTO;
import lk.ijse.cafe_au_lait.view.tdm.DeliveryTM;
import lk.ijse.cafe_au_lait.util.NotificationController;
import lk.ijse.cafe_au_lait.util.StageController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import static lk.ijse.cafe_au_lait.util.TextFieldBorderController.txtfieldbordercolor;

public class DeliveryDetailsFormController {

    String orderId;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private AnchorPane ancPane;
    @FXML
    private JFXButton checkOrederDetailsBtn;
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
    private ImageView searchIcon;
    @FXML
    private TextField searchIdTxt;
    @FXML
    private TextField searchDeliveryId;
    @FXML
    private TextField searchEmpId;
    @FXML
    private TextField deleiverIdTxt;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXButton checkOrders;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXComboBox<String> employeeIdTxt;

    @FXML
    private TextField locationTxt;
    @FXML
    private JFXComboBox<String> orderIdTxt;

    @FXML
    private TableView<DeliveryTM> tblDelivery;

    DeliveryBO deliveryBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.DELIVERY);


    @FXML
    void checkOrederDetailsBtnCllick(ActionEvent event) {
        StageController.changeScene("/view/orderDetailsForm.fxml", ancPane);

    }

    @FXML
    void checkOrdersClick(ActionEvent event) {
        StageController.changeScene("/view/checkOrders.fxml", ancPane);

    }


    @FXML
    void searchTable(KeyEvent event) throws SQLException {
        String searchValue = searchIdTxt.getText().trim();
        ArrayList<DeliveryDTO> load = deliveryBO.getAllDelivery();
        ObservableList<DeliveryTM> obList=FXCollections.observableArrayList();
        for (DeliveryDTO deliveryDatum : load) {
            obList.add(new DeliveryTM(deliveryDatum.getDeliverId(),deliveryDatum.getLocation(),deliveryDatum.getOrderId(),deliveryDatum.getEmpId()));
        }

        if (!searchValue.isEmpty()) {
            ObservableList<DeliveryTM> filteredData = obList.filtered(new Predicate<DeliveryTM>() {
                @Override
                public boolean test(DeliveryTM deliveryTM) {
                    return String.valueOf(deliveryTM.getOrderId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblDelivery.setItems(filteredData);
        } else {
            tblDelivery.setItems(obList);
        }


    }

    @FXML
    void searchTableByDeliveryId(KeyEvent event) throws SQLException {
        String searchValue = searchDeliveryId.getText().trim();
        ArrayList<DeliveryDTO> load = deliveryBO.getAllDelivery();
        ObservableList<DeliveryTM> obList=FXCollections.observableArrayList();
        for (DeliveryDTO deliveryDatum : load) {
            obList.add(new DeliveryTM(deliveryDatum.getDeliverId(),deliveryDatum.getLocation(),deliveryDatum.getOrderId(),deliveryDatum.getEmpId()));
        }

        if (!searchValue.isEmpty()) {
            ObservableList<DeliveryTM> filteredData = obList.filtered(new Predicate<DeliveryTM>() {
                @Override
                public boolean test(DeliveryTM deliveryTM) {
                    return String.valueOf(deliveryTM.getDeliveryId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblDelivery.setItems(filteredData);
        } else {
            tblDelivery.setItems(obList);
        }


    }

    @FXML
    void searchTableByEmpId(KeyEvent event) throws SQLException {
        String searchValue = searchEmpId.getText().trim();
        ArrayList<DeliveryDTO> load = deliveryBO.getAllDelivery();
        ObservableList<DeliveryTM> obList=FXCollections.observableArrayList();
        for (DeliveryDTO deliveryDatum : load) {
            obList.add(new DeliveryTM(deliveryDatum.getDeliverId(),deliveryDatum.getLocation(),deliveryDatum.getOrderId(),deliveryDatum.getEmpId()));
        }

        if (!searchValue.isEmpty()) {
            ObservableList<DeliveryTM> filteredData = obList.filtered(new Predicate<DeliveryTM>() {
                @Override
                public boolean test(DeliveryTM deliveryTM) {
                    return String.valueOf(deliveryTM.getEmployeeId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblDelivery.setItems(filteredData);
        } else {
            tblDelivery.setItems(obList);
        }

    }

    @FXML
    void tblClick(MouseEvent event) {
        DeliveryTM deliveryTM = tblDelivery.getSelectionModel().getSelectedItem();
        deleiverIdTxt.setText(deliveryTM.getDeliveryId());
        orderIdTxt.setValue(deliveryTM.getOrderId());
        employeeIdTxt.setValue(deliveryTM.getEmployeeId());
        locationTxt.setText(deliveryTM.getLocation());

    }

    void getAll() {
        tblDelivery.getItems().clear();
        try {
            ArrayList<DeliveryDTO> deliveryData = deliveryBO.getAllDelivery();
            ObservableList<DeliveryTM> obList= FXCollections.observableArrayList();
            for (DeliveryDTO deliveryDatum : deliveryData) {
                obList.add(new DeliveryTM(deliveryDatum.getDeliverId(),deliveryDatum.getLocation(),deliveryDatum.getOrderId(),deliveryDatum.getEmpId()));
            }

            tblDelivery.setItems(obList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @FXML
    void searchDeliveryIconClick(MouseEvent event) {

        try {
            DeliveryDTO deliveryDTO = deliveryBO.searchByDeliveryId(searchDeliveryId.getText());
            orderIdTxt.setValue(deliveryDTO.getOrderId());
            deleiverIdTxt.setText(deliveryDTO.getDeliverId());
            locationTxt.setText(deliveryDTO.getLocation());
            employeeIdTxt.setValue(deliveryDTO.getEmpId());

        } catch (Exception throwables) {
            System.out.println(throwables);
        }

    }

    @FXML
    void searchEmpIconClick(MouseEvent event) {

    }

    @FXML
    void searchOrderIconClick(MouseEvent event) {

    }


    void getCellValueFactory() {
        coiDeliveryId.setCellValueFactory(new PropertyValueFactory<>("deliveryId"));
        colLocattion.setCellValueFactory(new PropertyValueFactory<>("location"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colEmployeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));


    }

    public void deleteBtnCLick(ActionEvent actionEvent) {
        try {
            boolean result = NotificationController.confirmationMasseage("Are you sure want to delete this delivery");
            if (result) {
                boolean isDeleted = deliveryBO.deleteDeliveryById(deleiverIdTxt.getText());
                if (isDeleted) {
                    getAll();
                    NotificationController.animationMesseage("/assets/images/tick.gif", "delete", "Delivery deleted sucessfull !!");

                    String message = "No";
                    boolean isUpdated = deliveryBO.updateDeliveyMode(orderIdTxt.getValue(), message);

                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void updateBtnClicl(ActionEvent event) {
        String deliveryId = deleiverIdTxt.getText();
        String orderId = orderIdTxt.getValue();
        String empId = String.valueOf(employeeIdTxt.getValue());
        String location = locationTxt.getText();
        DeliveryDTO newDeliverDto = new DeliveryDTO(deliveryId, location, orderId, empId);

        try {
            boolean result = NotificationController.confirmationMasseage("Are you sure want to update this delivery");
            if (result) {
                boolean isUpdated = deliveryBO.updateDelivery(newDeliverDto);
                if (isUpdated) {
                    getAll();
                    NotificationController.animationMesseage("/assets/images/tick.gif", "update", "Delivery updated sucessfull !!");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    void loadEmpIds() {
        try {
            ArrayList<String> empIds = deliveryBO.loadEmpIds();
            ObservableList<String> obList=FXCollections.observableArrayList();
            for (String empId : empIds) {
                obList.add(empId);
            }
            employeeIdTxt.setItems(obList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void loadOrderIds() {
        try {
            ArrayList<String> orderIds = deliveryBO.loadOrderIds();
            ObservableList<String> obList=FXCollections.observableArrayList();
            for (String id : orderIds) {
                obList.add(id);
            }

            orderIdTxt.setItems(obList);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }


    @FXML
    void initialize() {
        txtfieldbordercolor(deleiverIdTxt);
        txtfieldbordercolor(locationTxt);
        loadEmpIds();
        loadOrderIds();
        getAll();
        getCellValueFactory();

    }


}
