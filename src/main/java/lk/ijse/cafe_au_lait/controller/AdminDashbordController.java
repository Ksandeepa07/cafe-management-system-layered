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

public class AdminDashbordController {

    int x = 0;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Label timeLbl;
    @FXML
    private Label datLbl;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private JFXButton homeBtn;
    @FXML
    private JFXButton employeeBtn;
    @FXML
    private JFXButton supplierBtn;
    @FXML
    private JFXButton inventoryBtn;
    @FXML
    private JFXButton ordersBtn;
    @FXML
    private JFXButton reportsBtn;
    @FXML
    private JFXButton slalaryBtn;
    @FXML
    private AnchorPane ancPane;
    @FXML
    private Label lblStatus;

    public void homeBtnClick(javafx.event.ActionEvent actionEvent) throws IOException {
//        btncolor(homeBtn,ancPane);
        StageController.changeScene("/view/cashierHomeForm.fxml", ancPane);
    }

    public void employeeBtnClick(ActionEvent actionEvent) throws IOException {
//        btncolor(employeeBtn,ancPane);
        StageController.changeScene("/view/adminEmployee.fxml", ancPane);
    }

    @FXML
    void supplierBtnClick(ActionEvent event) throws IOException {
        StageController.changeScene("/view/adminSupplier.fxml", ancPane);
    }

    @FXML
    void inventoryBtnClick(ActionEvent event) throws IOException {
        StageController.changeScene("/view/cashierInventory.fxml", ancPane);

    }

    @FXML
    void ordersBtnClick(ActionEvent event) throws IOException {
        StageController.changeScene("/view/checkOrders.fxml", ancPane);
    }

    @FXML
    void reportsBttnClick(ActionEvent event) throws IOException {
        StageController.changeScene("/view/adminReports.fxml", ancPane);
    }

    public void salaryBtnClick(ActionEvent actionEvent) throws IOException {
        StageController.changeScene("/view/adminSalary.fxml", ancPane);
    }

    public void logoutClick(MouseEvent mouseEvent) throws IOException {
        anchorpane.getScene().getWindow().hide();
        StageController.changeStage("/view/loginPage.fxml", "Login");
    }

//    public static void btncolor(Button btn, AnchorPane anchorPane){
//        btn.setStyle("-fx-background-color: #8c0c0c;");
//        anchorPane.getChildren().addListener((ListChangeListener<Node>) change -> {
//            while (change.next()) {
//                if (change.wasAdded()) {
//                    for (Node node : change.getAddedSubList()) {
//                        if (node instanceof AnchorPane) {
//                            // Check if the new node is an AnchorPane
//                            AnchorPane newAnchorPane = (AnchorPane) node;
//                            if (newAnchorPane.getId().equals("ancPane")) {
//                                btn.setStyle("-fx-background-color: #dfa47e;");
//
//                            } else {
//                                btn.setStyle("-fx-background-color: #dfa47e;");
//
//                            }
//                        }
//                    }
//                }
//            }
//        });
//    }

    @FXML
    void initialize() {
        TimeController.timeNow(timeLbl, datLbl);

    }


}
