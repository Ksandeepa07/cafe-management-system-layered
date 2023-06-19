package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe_au_lait.bo.BOFactory;
import lk.ijse.cafe_au_lait.bo.custom.OrdersBO;
import lk.ijse.cafe_au_lait.dto.CustomerDTO;
import lk.ijse.cafe_au_lait.dto.OrdersDTO;
import lk.ijse.cafe_au_lait.view.tdm.CheckOrdersTM;
import lk.ijse.cafe_au_lait.controller.util.StageController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class CheckOrderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private JFXButton checkDeliveryDetailsBtn;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colDelivery;

    @FXML
    private TableColumn<?, ?> colOderId;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderPayment;

    @FXML
    private TableColumn<?, ?> colOrderTime;

    @FXML
    private TextField searchDeliveryId;


    @FXML
    private TextField searchCustId;

    @FXML
    private ImageView searchIcon;

    @FXML
    private ImageView searchIconCustomer;

    @FXML
    private Label TototalOrderPlacedLbl;

    @FXML
    private Label customerNameLbl;
    @FXML
    private ImageView searchIconDate;


    @FXML
    private TextField searchIdTxt;

    @FXML
    private TextField searchByOrderDate;

    @FXML
    private TableView<CheckOrdersTM> tblCheckOrders;

    @FXML
    private Label totalOrdersMailLbl;
    @FXML
    private Label customerNameMainLbl;

    @FXML
    private Group customerGroup;

    @FXML
    private Label tot6alOrderForDate;

    @FXML
    private Group dateGroup;

    @FXML
    private JFXButton backBtn;

    OrdersBO ordersBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDERS);

    @FXML
    void checkdeliveryDetailsBtnCllick(ActionEvent event) {

    }

    @FXML
    void searchIconClick(MouseEvent event) {

    }

    @FXML
    void backBtnClick(ActionEvent event) {
        StageController.changeScene("/view/cashierOrdeForm.fxml ", ancPane);

    }


    @FXML
    void searchIconClickDate(MouseEvent event) {
        try {
            customerGroup.setVisible(false);
            dateGroup.setVisible(true);
            int count = ordersBO.countOrdersOnDay(searchByOrderDate.getText());
            tot6alOrderForDate.setText(String.valueOf(count));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @FXML
    void searchIconCustomerClick(MouseEvent event) throws SQLException {
        dateGroup.setVisible(false);
        customerGroup.setVisible(true);
        CustomerDTO customerDTO = ordersBO.searchCustomerById(searchCustId.getText());
        customerNameLbl.setText(customerDTO.getCustName());
        try {
            int id = Integer.parseInt(String.valueOf(ordersBO.countOrdersPlaceBYCustomer(searchCustId.getText())));
            TototalOrderPlacedLbl.setText(String.valueOf(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    @FXML
    void tblClick(MouseEvent event) {

    }

    void getAll() {
        tblCheckOrders.getItems().clear();
        try {
            ArrayList<OrdersDTO> orderData = ordersBO.getAllOrders();
            ObservableList<CheckOrdersTM> obList= FXCollections.observableArrayList();
            for (OrdersDTO orderDatum : orderData) {
                obList.add(new CheckOrdersTM(orderDatum.getOrderId(),orderDatum.getCustId(),orderDatum.getOrderDate(),orderDatum.getOrderTime(),orderDatum.getOrderPayment(),
                        orderDatum.getDelivery()));
            }
            tblCheckOrders.setItems(obList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void getCellValueFactory() {
        colOderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colOrderTime.setCellValueFactory(new PropertyValueFactory<>("orderTime"));
        colOrderPayment.setCellValueFactory(new PropertyValueFactory<>("orderPayment"));
        colDelivery.setCellValueFactory(new PropertyValueFactory<>("delivery"));


    }

    public void searchTableByorderDate(KeyEvent keyEvent) throws SQLException {
        String searchValue = searchByOrderDate.getText().trim();
        ArrayList<OrdersDTO> load = ordersBO.getAllOrders();
        ObservableList<CheckOrdersTM> obList=FXCollections.observableArrayList();
        for (OrdersDTO ordersDTO : load) {
            obList.add(new CheckOrdersTM(ordersDTO.getOrderId(),ordersDTO.getCustId(),ordersDTO.getOrderDate(),ordersDTO.getOrderTime(),ordersDTO.getOrderPayment(),ordersDTO.getDelivery()));
        }

        if (!searchValue.isEmpty()) {
            ObservableList<CheckOrdersTM> filteredData = obList.filtered(new Predicate<CheckOrdersTM>() {
                @Override
                public boolean test(CheckOrdersTM checkOrdersTM) {
                    return String.valueOf(checkOrdersTM.getOrderDate()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblCheckOrders.setItems(filteredData);
        } else {
            tblCheckOrders.setItems(obList);
        }

    }

    @FXML
    void searchTable(KeyEvent event) throws SQLException {
        String searchValue = searchIdTxt.getText().trim();
        ArrayList<OrdersDTO> load = ordersBO.getAllOrders();
        ObservableList<CheckOrdersTM> obList=FXCollections.observableArrayList();
        for (OrdersDTO ordersDTO : load) {
            obList.add(new CheckOrdersTM(ordersDTO.getOrderId(),ordersDTO.getCustId(),ordersDTO.getOrderDate(),ordersDTO.getOrderTime(),ordersDTO.getOrderPayment(),ordersDTO.getDelivery()));
        }

        if (!searchValue.isEmpty()) {
            ObservableList<CheckOrdersTM> filteredData = obList.filtered(new Predicate<CheckOrdersTM>() {
                @Override
                public boolean test(CheckOrdersTM checkOrdersTM) {
                    return String.valueOf(checkOrdersTM.getOrderId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblCheckOrders.setItems(filteredData);
        } else {
            tblCheckOrders.setItems(obList);
        }

    }


    @FXML
    void searchTableByCustId(KeyEvent event) throws SQLException {
        String searchValue = searchCustId.getText().trim();
        ArrayList<OrdersDTO> load = ordersBO.getAllOrders();
        ObservableList<CheckOrdersTM> obList=FXCollections.observableArrayList();
        for (OrdersDTO ordersDTO : load) {
            obList.add(new CheckOrdersTM(ordersDTO.getOrderId(),ordersDTO.getCustId(),ordersDTO.getOrderDate(),ordersDTO.getOrderTime(),ordersDTO.getOrderPayment(),ordersDTO.getDelivery()));
        }

        if (!searchValue.isEmpty()) {
            ObservableList<CheckOrdersTM> filteredData = obList.filtered(new Predicate<CheckOrdersTM>() {
                @Override
                public boolean test(CheckOrdersTM checkOrdersTM) {
                    return String.valueOf(checkOrdersTM.getCustId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblCheckOrders.setItems(filteredData);
        } else {
            tblCheckOrders.setItems(obList);
        }

    }

    @FXML
    void initialize() {
        customerGroup.setVisible(false);
        dateGroup.setVisible(false);

        getAll();
        getCellValueFactory();

    }


}
