package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import lk.ijse.cafe_au_lait.bo.BOFactory;
import lk.ijse.cafe_au_lait.bo.custom.EmployeeBO;
import lk.ijse.cafe_au_lait.bo.custom.SalaryBO;
import lk.ijse.cafe_au_lait.dto.SalaryDTO;
import lk.ijse.cafe_au_lait.view.tdm.SalaryTM;
import lk.ijse.cafe_au_lait.util.DataValidateController;
import lk.ijse.cafe_au_lait.util.NotificationController;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import static lk.ijse.cafe_au_lait.util.TextFieldBorderController.txtfieldbordercolor;

public class AdminSalaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private TextField payamentTxt;

    @FXML
    private TextField salaryTxt;

    @FXML
    private TableView<SalaryTM> tbllsalary;

    @FXML
    private TableColumn<?, ?> colempId;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colMethod;

    @FXML
    private TableColumn<SalaryTM, Double> colPayment;

    @FXML
    private TableColumn<SalaryTM, Double> colOverTime;

    @FXML
    private TextField overTimeTxt;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private TextField searchIdTxt;

    @FXML
    private ImageView searchIcon;

    @FXML
    private JFXComboBox<String> idTxt;

    @FXML
    private JFXComboBox<String> methodTxt;

    @FXML
    private ImageView paymentICon;

    @FXML
    private ImageView paymentMethodIcon;

    @FXML
    private ImageView salaryIdIcon;

    @FXML
    private ImageView emplyeeIdIcon;

    @FXML
    private ImageView overTimeIcon;

    EmployeeBO employeeBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.EMPLOYEE);
    SalaryBO salaryBO=BOFactory.getInstance().getBO(BOFactory.BOTypes.SALARY);


    @FXML
    void deleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted = salaryBO.deleteSalary(salaryTxt.getText());
            boolean result = NotificationController.confirmationMasseage("Are you sure you want delete this " +
                    "salary ?");
            if (result) {
                if (isDeleted) {
                    saveBtn.setDisable(true);
                    updateBtn.setDisable(true);
                    deleteBtn.setDisable(true);
                    emplyeeIdIcon.setVisible(false);
                    salaryIdIcon.setVisible(false);
                    paymentMethodIcon.setVisible(false);
                    paymentICon.setVisible(false);
                    overTimeIcon.setVisible(false);
                    idTxt.setValue(null);
                    salaryTxt.setText("");
                    methodTxt.setValue(null);
                    payamentTxt.setText("");
                    overTimeTxt.setText("");
                    getAll();
                    NotificationController.animationMesseage("/assets/images/tick.gif", "Delete",
                            "Salary Deleted sucessfully !!");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void idTxtClick(ActionEvent event) {
        if (!idTxt.getSelectionModel().isEmpty()){
            emplyeeIdIcon.setVisible(true);
        }else{
            emplyeeIdIcon.setVisible(false);
        }


    }

    @FXML
    void methodTxtClick(ActionEvent event) {
        if (!methodTxt.getSelectionModel().isEmpty()){
            paymentMethodIcon.setVisible(true);
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        if (idTxt.getSelectionModel().isEmpty() & methodTxt.getSelectionModel().isEmpty()) {
            NotificationController.ErrorMasseage("Employee Id and payment method can't be empty");
        } else if (idTxt.getSelectionModel().isEmpty()) {
            NotificationController.ErrorMasseage("Employee Id can't be empty");

        } else if (methodTxt.getSelectionModel().isEmpty()) {
            NotificationController.ErrorMasseage("Payment method can't be empty");
        }else{
            Double overTyime;
            String id = idTxt.getValue();
            String salaryId = salaryTxt.getText();
            String method = methodTxt.getValue();
            Double payment = Double.valueOf(payamentTxt.getText());
            if (overTimeTxt.getText().isEmpty()) {
                overTyime = 0.0;
            } else {
                overTyime = Double.valueOf(overTimeTxt.getText());
            }


            SalaryDTO salaryDTO = new SalaryDTO(id, salaryId, method, payment, overTyime);

            try {
                boolean isSaved = salaryBO.saveSalary(salaryDTO);
                if (isSaved) {
                    tbllsalary.refresh();
                    saveBtn.setDisable(true);
                    updateBtn.setDisable(true);
                    deleteBtn.setDisable(true);
                    emplyeeIdIcon.setVisible(false);
                    salaryIdIcon.setVisible(false);
                    paymentMethodIcon.setVisible(false);
                    paymentICon.setVisible(false);
                    overTimeIcon.setVisible(false);
                    idTxt.setValue(null);
                    salaryTxt.setText("");
                    methodTxt.setValue(null);
                    payamentTxt.setText("");
                    overTimeTxt.setText("");
                    getAll();
                    NotificationController.animationMesseage("/assets/images/tick.gif", "Saved",
                            "Salary Added sucessfully !!");
                }
            } catch (SQLIntegrityConstraintViolationException throwables) {
                NotificationController.ErrorMasseage("This Salary Id is Already Exsits");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }

    @FXML
    void searchIconClick(MouseEvent event) throws SQLException {
        SalaryDTO salaryDTO = salaryBO.searchSalaryById(searchIdTxt.getText());
        if (salaryDTO != null) {
            idTxt.setValue(salaryDTO.getEmpId());
            salaryTxt.setText(salaryDTO.getSalaryId());
            methodTxt.setValue(salaryDTO.getPaymentMethod());
            payamentTxt.setText(String.valueOf(salaryDTO.getPayment()));
            overTimeTxt.setText(String.valueOf(salaryDTO.getOverTime()));

        } else {
            NotificationController.ErrorMasseage("Event ID Not Found");
        }

    }

    @FXML
    void searchTable(KeyEvent event) throws SQLException {
        String searchValue = searchIdTxt.getText().trim();
        ArrayList<SalaryDTO> load = salaryBO.getAllSalary();
        ObservableList<SalaryTM> obList=FXCollections.observableArrayList();
        for (SalaryDTO salaryDTO : load) {
            obList.add(new SalaryTM(salaryDTO.getEmpId(),salaryDTO.getSalaryId(),salaryDTO.getPaymentMethod(),salaryDTO.getPayment(),salaryDTO.getOverTime()));
        }
        if (!searchValue.isEmpty()) {
            ObservableList<SalaryTM> filteredData = obList.filtered(new Predicate<SalaryTM>() {
                @Override
                public boolean test(SalaryTM salarytm) {
                    return String.valueOf(salarytm.getEmpId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tbllsalary.setItems(filteredData);
        } else {
            tbllsalary.setItems(obList);
        }

    }

    @FXML
    void updateOnAction(ActionEvent event) {
        Double overTyime;
        String id = idTxt.getValue();
        String salaryId = salaryTxt.getText();
        String method = methodTxt.getValue();
        Double payment = Double.valueOf(payamentTxt.getText());
        if (overTimeTxt.getText().isEmpty()) {
            overTyime = 0.0;
        } else {
            overTyime = Double.valueOf(overTimeTxt.getText());
        }
        SalaryDTO salaryDTO = new SalaryDTO(id, salaryId, method, payment, overTyime);
        try {
            boolean isUpdated = salaryBO.updateSalary(salaryDTO);
            boolean result = NotificationController.confirmationMasseage("Are you sure you want update this " +
                    "salary ?");
            if (result) {
                if (isUpdated) {
                    saveBtn.setDisable(true);
                    updateBtn.setDisable(true);
                    deleteBtn.setDisable(true);
                    emplyeeIdIcon.setVisible(false);
                    salaryIdIcon.setVisible(false);
                    paymentMethodIcon.setVisible(false);
                    paymentICon.setVisible(false);
                    overTimeIcon.setVisible(false);
                    idTxt.setValue(null);
                    salaryTxt.setText("");
                    methodTxt.setValue(null);
                    payamentTxt.setText("");
                    overTimeTxt.setText("");
                    getAll();
                    NotificationController.animationMesseage("/assets/images/tick.gif", "Update",
                            "Salary Updated sucessfully !!");

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    void getAll() {
        tbllsalary.getItems().clear();
        try {
            ArrayList<SalaryDTO> salaryData = salaryBO.getAllSalary();
            for (SalaryDTO salaryDatum : salaryData) {
                tbllsalary.getItems().add(new SalaryTM(salaryDatum.getEmpId(),salaryDatum.getSalaryId(),salaryDatum.getPaymentMethod(),salaryDatum.getPayment(),
                        salaryDatum.getOverTime()));
            }
//            tbllsalary.setItems(salaryData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void getCellValueFactory() {
        colempId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colOverTime.setCellValueFactory(new PropertyValueFactory<>("overTime"));

        ////////////////////
        colPayment.setCellFactory(new Callback<TableColumn<SalaryTM, Double>, TableCell<SalaryTM, Double>>() {
            private final DecimalFormat format = new DecimalFormat("#.00");

            @Override
            public TableCell<SalaryTM, Double> call(TableColumn<SalaryTM, Double> column) {
                return new TableCell<SalaryTM, Double>() {
                    @Override
                    protected void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(format.format(item));
                        }
                    }
                };
            }
        });

        colOverTime.setCellFactory(new Callback<TableColumn<SalaryTM, Double>, TableCell<SalaryTM, Double>>() {
            private final DecimalFormat format = new DecimalFormat("#.00");

            @Override
            public TableCell<SalaryTM, Double> call(TableColumn<SalaryTM, Double> column) {
                return new TableCell<SalaryTM, Double>() {
                    @Override
                    protected void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(format.format(item));
                        }
                    }
                };
            }
        });
    }

    public void tblClick(MouseEvent mouseEvent) {
        salaryIdIcon.setVisible(true);
        overTimeIcon.setVisible(true);
        paymentICon.setVisible(true);

        saveBtn.setDisable(false);
        updateBtn.setDisable(false);
        deleteBtn.setDisable(false);
        TablePosition pos = tbllsalary.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<SalaryTM, ?>> columns = tbllsalary.getColumns();

        idTxt.setValue(columns.get(0).getCellData(row).toString());
        salaryTxt.setText(columns.get(1).getCellData(row).toString());
        methodTxt.setValue(columns.get(2).getCellData(row).toString());
        payamentTxt.setText(columns.get(3).getCellData(row).toString());
        overTimeTxt.setText(columns.get(4).getCellData(row).toString());
    }


    void loadEmployeeId() {
        try {
            ArrayList<String> employeeData = salaryBO.loadEmployeeIds();
            ObservableList<String> loadIds= FXCollections.observableArrayList();
            for (String employeeDatum : employeeData) {
                loadIds.add(employeeDatum);
            }
            idTxt.setItems(loadIds);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void overTimeKeyTyped(KeyEvent event) {
        boolean isValidate= DataValidateController.priceValidate(overTimeTxt.getText());
        saveBtn.setDisable(!isValidate|payamentTxt.getText().isEmpty()|salaryTxt.getText().isEmpty());
        updateBtn.setDisable(!isValidate|payamentTxt.getText().isEmpty()|salaryTxt.getText().isEmpty());
        deleteBtn.setDisable(!isValidate|payamentTxt.getText().isEmpty()|salaryTxt.getText().isEmpty());
        if (isValidate){
            overTimeIcon.setVisible(true);
        }else {
            overTimeIcon.setVisible(false);
        }

    }

    @FXML
    void paymentKeyTyped(KeyEvent event) {
        boolean isValidate= DataValidateController.priceValidate(payamentTxt.getText());
        saveBtn.setDisable(!isValidate|overTimeTxt.getText().isEmpty()|salaryTxt.getText().isEmpty());
        updateBtn.setDisable(!isValidate|overTimeTxt.getText().isEmpty()|salaryTxt.getText().isEmpty());
        deleteBtn.setDisable(!isValidate|overTimeTxt.getText().isEmpty()|salaryTxt.getText().isEmpty());
        if (isValidate){
            paymentICon.setVisible(true);
        }else {
            paymentICon.setVisible(false);
        }

    }

    @FXML
    void salaryIdKeyTyped(KeyEvent event) {
        boolean isValidate= DataValidateController.salaryIdValidate(salaryTxt.getText());
        saveBtn.setDisable(!isValidate|payamentTxt.getText().isEmpty()|overTimeTxt.getText().isEmpty());
        updateBtn.setDisable(!isValidate|payamentTxt.getText().isEmpty()|overTimeTxt.getText().isEmpty());
        deleteBtn.setDisable(!isValidate|payamentTxt.getText().isEmpty()|overTimeTxt.getText().isEmpty());
        if (isValidate){
            salaryIdIcon.setVisible(true);
        }else {
            salaryIdIcon.setVisible(false);
        }

    }

    @FXML
    void initialize() {
        saveBtn.setDisable(true);
        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);
        txtfieldbordercolor(salaryTxt);
        txtfieldbordercolor(payamentTxt);
        txtfieldbordercolor(overTimeTxt);
        getAll();
        getCellValueFactory();
        loadEmployeeId();
        methodTxt.getItems().addAll("Card", "Cash");


    }


}
