package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.cafe_au_lait.dto.Delivery;
import lk.ijse.cafe_au_lait.view.tdm.DeliveryTM;
import lk.ijse.cafe_au_lait.model.DeliveryModel;
import lk.ijse.cafe_au_lait.model.EmployeeModel;
import lk.ijse.cafe_au_lait.model.OrderModel;
import lk.ijse.cafe_au_lait.util.NotificationController;
import lk.ijse.cafe_au_lait.util.StageController;

import java.net.URL;
import java.sql.SQLException;
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
        ObservableList<DeliveryTM> obList = DeliveryModel.getAll();

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
        ObservableList<DeliveryTM> obList = DeliveryModel.getAll();

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
        ObservableList<DeliveryTM> obList = DeliveryModel.getAll();

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
        try {
            ObservableList<DeliveryTM> deliveryData = DeliveryModel.getAll();

            tblDelivery.setItems(deliveryData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @FXML
    void searchDeliveryIconClick(MouseEvent event) {

        try {
            Delivery delivery = DeliveryModel.searchByDeliveryId(searchDeliveryId.getText());
            orderIdTxt.setValue(delivery.getOrderId());
            deleiverIdTxt.setText(delivery.getDeliverId());
            locationTxt.setText(delivery.getLocation());
            employeeIdTxt.setValue(delivery.getEmpId());

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
                boolean isDeleted = DeliveryModel.deleteById(deleiverIdTxt.getText());
                if (isDeleted) {
                    getAll();
                    NotificationController.animationMesseage("/assets/tick.gif", "delete", "Delivery deleted sucessfull !!");

                    String message = "No";
                    boolean isUpdated = OrderModel.updateDeliveyMode(orderIdTxt.getValue(), message);

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
        Delivery newDeliverDto = new Delivery(deliveryId, location, orderId, empId);

        try {
            boolean result = NotificationController.confirmationMasseage("Are you sure want to update this delivery");
            if (result) {
                boolean isUpdated = DeliveryModel.update(newDeliverDto);
                if (isUpdated) {
                    getAll();
                    NotificationController.animationMesseage("/assets/tick.gif", "update", "Delivery updated sucessfull !!");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    void loadEmpIds() {
//        try {
//            ObservableList<String> empIds = EmployeeModel.loadEmpIds();
//            employeeIdTxt.setItems(empIds);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }

    void loadOrderIds() {
        try {
            ObservableList<String> orderIds = OrderModel.loadOrderIds();
            orderIdTxt.setItems(orderIds);
        } catch (SQLException throwables) {
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
