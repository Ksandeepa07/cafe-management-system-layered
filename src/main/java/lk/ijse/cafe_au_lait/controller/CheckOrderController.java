package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.cafe_au_lait.dto.CustomerDTO;
import lk.ijse.cafe_au_lait.view.tdm.CheckOrdersTM;
import lk.ijse.cafe_au_lait.model.CheckOrdersModel;
import lk.ijse.cafe_au_lait.model.CustomerModel;
import lk.ijse.cafe_au_lait.util.StageController;

import java.net.URL;
import java.sql.SQLException;
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
            String count = CheckOrdersModel.countOrdersOnDay(searchByOrderDate.getText());
            tot6alOrderForDate.setText(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @FXML
    void searchIconCustomerClick(MouseEvent event) {
//        dateGroup.setVisible(false);
//        customerGroup.setVisible(true);
//        CustomerDTO customerDTO = CustomerModel.searchById(searchCustId.getText());
//        customerNameLbl.setText(customerDTO.getCustName());
//        try {
//            String id = CheckOrdersModel.countOrders(searchCustId.getText());
//            TototalOrderPlacedLbl.setText(String.valueOf(id));
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

    }


    @FXML
    void tblClick(MouseEvent event) {

    }

    void getAll() {
        try {
            ObservableList<CheckOrdersTM> orderData = CheckOrdersModel.getAll();
            tblCheckOrders.setItems(orderData);
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
        ObservableList<CheckOrdersTM> obList = CheckOrdersModel.getAll();

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
        ObservableList<CheckOrdersTM> obList = CheckOrdersModel.getAll();

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
        ObservableList<CheckOrdersTM> obList = CheckOrdersModel.getAll();

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
