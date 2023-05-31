package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe_au_lait.dto.CustomerDTO;
import lk.ijse.cafe_au_lait.dto.Employee;
import lk.ijse.cafe_au_lait.view.tdm.EmployeeTM;
import lk.ijse.cafe_au_lait.model.EmployeeModel;
import lk.ijse.cafe_au_lait.util.DataValidateController;
import lk.ijse.cafe_au_lait.util.NotificationController;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import static lk.ijse.cafe_au_lait.util.TextFieldBorderController.txtfieldbordercolor;

public class AdminEmployeeCntroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField nicTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private ComboBox jobTitileTxt;

    @FXML
    private DatePicker dobTxt;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colJobTitle;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TextField contactTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private ImageView searchIcon;

    @FXML
    private TextField searchIdTxt;

    @FXML
    private Label contactCheckLb;

    @FXML
    private Label emailCheckLbl;

    @FXML
    private ImageView adressIcon;

    @FXML
    private ImageView dobIcon;

    @FXML
    private ImageView contactIcon;

    @FXML
    private ImageView emailIcon;

    @FXML
    private ImageView empIdIcon;

    @FXML
    private ImageView empNameIcon;

    @FXML
    private ImageView jobTitleIcon;

    @FXML
    private ImageView nicIcon;

    boolean isEmpValidate;
    boolean isNameValidate;
    boolean isAddressValidate;
    boolean isNicValidate;
    boolean isPhoneNumberValidate;
    boolean isEmailValidate;


    @FXML
    void saveOnAction(ActionEvent event) {
        if (jobTitileTxt.getSelectionModel().isEmpty() & dobTxt.getEditor().getText().isEmpty()) {
            NotificationController.ErrorMasseage("Job title and date of birth can't be empty");
        } else if (jobTitileTxt.getSelectionModel().isEmpty()) {
            NotificationController.ErrorMasseage("Job title can't be empty");

        } else if (dobTxt.getEditor().getText().isEmpty()) {
            NotificationController.ErrorMasseage("dob can't be empty");

        } else {
            String id = idTxt.getText();
            String name = nameTxt.getText();
            String address = addressTxt.getText();
            String dob = String.valueOf(dobTxt.getValue());
            String nic = nicTxt.getText();
            String jobTitle = (String) jobTitileTxt.getValue();
            String contact = contactTxt.getText();
            String email = emailTxt.getText();

            CustomerDTO customerDTO = new CustomerDTO(id, name, contact, email);
            Employee employee = new Employee(id, name, address, dob, nic, jobTitle, contact, email);

            Boolean isSaved = null;
            try {
                isSaved = EmployeeModel.save(employee);
                if (isSaved) {
                    saveBtn.setDisable(true);
                    updateBtn.setDisable(true);
                    deleteBtn.setDisable(true);
                    empIdIcon.setVisible(false);
                    empNameIcon.setVisible(false);
                    adressIcon.setVisible(false);
                    contactIcon.setVisible(false);
                    emailIcon.setVisible(false);
                    nicIcon.setVisible(false);
                    idTxt.setText(" ");
                    nameTxt.setText(" ");
                    addressTxt.setText(" ");
                    jobTitileTxt.setValue(null);
                    dobTxt.setValue(null);
                    nicTxt.setText("");
                    contactTxt.setText("");
                    emailTxt.setText("");

                    NotificationController.animationMesseage("/assets/tick.gif", "Saved",
                            "Employee Added sucessfully !!");
                    getAll();

                }
            } catch (SQLIntegrityConstraintViolationException throwables) {
                NotificationController.ErrorMasseage("This Employee Id is Already Exsits");
            } catch (MysqlDataTruncation throwables) {
                System.out.println();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }

    void getAll() {
        try {
            ObservableList<EmployeeTM> employeeData = EmployeeModel.getAll();
            tblEmployee.setItems(employeeData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void getCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colJobTitle.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    public void searchIconClick(MouseEvent mouseEvent) {
        try {
            Employee employee = EmployeeModel.searchById(searchIdTxt.getText());
            if (employee != null) {
                idTxt.setText(employee.getId());
                nameTxt.setText(employee.getName());
                addressTxt.setText(employee.getAddress());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(employee.getDob(), formatter);
                dobTxt.setValue(date);
                nicTxt.setText(employee.getNic());
                jobTitileTxt.setValue(employee.getJobTitle());
                contactTxt.setText(employee.getContact());
                emailTxt.setText(employee.getEmail());
            } else {
                NotificationController.ErrorMasseage("Employee ID Not Found");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void searchTable(KeyEvent keyEvent) throws SQLException {
        String searchValue = searchIdTxt.getText().trim();
        ObservableList<EmployeeTM> obList = EmployeeModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<EmployeeTM> filteredData = obList.filtered(new Predicate<EmployeeTM>() {
                @Override
                public boolean test(EmployeeTM employeetm) {
                    return String.valueOf(employeetm.getId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblEmployee.setItems(filteredData);
        } else {
            tblEmployee.setItems(obList);
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        if (jobTitileTxt.getSelectionModel().isEmpty() & dobTxt.getEditor().getText().isEmpty()) {
            NotificationController.ErrorMasseage("Job title and date of birth can't be empty");
        } else if (jobTitileTxt.getSelectionModel().isEmpty()) {
            NotificationController.ErrorMasseage("Job title can't be empty");

        } else if (dobTxt.getEditor().getText().isEmpty()) {
            NotificationController.ErrorMasseage("dob can't be empty");
        } else {
            String id = idTxt.getText();
            String name = nameTxt.getText();
            String address = addressTxt.getText();
            String dob = String.valueOf(dobTxt.getValue());
            String nic = nicTxt.getText();
            String jobTitle = (String) jobTitileTxt.getValue();
            String contact = contactTxt.getText();
            String email = emailTxt.getText();


            Employee employee = new Employee(id, name, address, dob, nic, jobTitle, contact, email);
            try {
                boolean isUpdated = EmployeeModel.update(employee);
                boolean result = NotificationController.confirmationMasseage("Are you sure you want update this " +
                        "employee ?");
                if (result) {
                    if (isUpdated) {
                        saveBtn.setDisable(true);
                        updateBtn.setDisable(true);
                        deleteBtn.setDisable(true);
                        empIdIcon.setVisible(false);
                        empNameIcon.setVisible(false);
                        adressIcon.setVisible(false);
                        contactIcon.setVisible(false);
                        emailIcon.setVisible(false);
                        nicIcon.setVisible(false);
                        idTxt.setText("");
                        nameTxt.setText("");
                        addressTxt.setText("");
                        jobTitileTxt.setValue(null);
                        dobTxt.setValue(null);
                        nicTxt.setText("");
                        contactTxt.setText("");
                        emailTxt.setText("");

                        getAll();
                        NotificationController.animationMesseage("/assets/tick.gif", "Update",
                                "Employee Updated sucessfully !!");
                    }
                }

            } catch (MysqlDataTruncation throwables) {
                System.out.println();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {
        try {
            boolean isDeleted = EmployeeModel.delete(idTxt.getText());
            boolean result = NotificationController.confirmationMasseage("Are you sure you want delete this " +
                    "employee ?");
            if (result) {
                if (isDeleted) {
                    getAll();
                    NotificationController.animationMesseage("/assets/tick.gif", "Delete",
                            "Employee Deleted sucessfully !!");
                    saveBtn.setDisable(true);
                    updateBtn.setDisable(true);
                    deleteBtn.setDisable(true);
                    empIdIcon.setVisible(false);
                    empNameIcon.setVisible(false);
                    adressIcon.setVisible(false);
                    contactIcon.setVisible(false);
                    emailIcon.setVisible(false);
                    nicIcon.setVisible(false);
                    idTxt.setText("");
                    nameTxt.setText("");
                    addressTxt.setText("");
                    jobTitileTxt.setValue(null);
                    dobTxt.setValue(null);
                    nicTxt.setText("");
                    contactTxt.setText("");
                    emailTxt.setText("");

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void tblClick(MouseEvent mouseEvent) {
        empIdIcon.setVisible(true);
        nicIcon.setVisible(true);
        empNameIcon.setVisible(true);
        contactIcon.setVisible(true);
        emailIcon.setVisible(true);
        adressIcon.setVisible(true);

        saveBtn.setDisable(false);
        updateBtn.setDisable(false);
        deleteBtn.setDisable(false);
        try{
            TablePosition pos = tblEmployee.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            // Get the data from the selected row
            ObservableList<TableColumn<EmployeeTM, ?>> columns = tblEmployee.getColumns();
            idTxt.setText(columns.get(0).getCellData(row).toString());
            nameTxt.setText(columns.get(1).getCellData(row).toString());
            addressTxt.setText(columns.get(2).getCellData(row).toString());
            dobTxt.setValue(LocalDate.parse(columns.get(3).getCellData(row).toString()));
            nicTxt.setText(columns.get(4).getCellData(row).toString());
            jobTitileTxt.setValue(columns.get(5).getCellData(row).toString());
            contactTxt.setText(columns.get(6).getCellData(row).toString());
            emailTxt.setText(columns.get(7).getCellData(row).toString());
        }catch ( Exception e){
            empIdIcon.setVisible(false);
            nicIcon.setVisible(false);
            empNameIcon.setVisible(false);
            contactIcon.setVisible(false);
            emailIcon.setVisible(false);
            adressIcon.setVisible(false);

            saveBtn.setDisable(true);
            updateBtn.setDisable(true);
            deleteBtn.setDisable(true);
        }

    }

    @FXML
    void adressKeyTyped(KeyEvent event) {
        isAddressValidate = DataValidateController.addressValidate(addressTxt.getText());

            saveBtn.setDisable(!isAddressValidate | nameTxt.getText().isEmpty() | contactTxt.getText().isEmpty()
                    | idTxt.getText().isEmpty() | emailTxt.getText().isEmpty() |
                    nicTxt.getText().isEmpty());
            updateBtn.setDisable(!isAddressValidate | nameTxt.getText().isEmpty() | contactTxt.getText().isEmpty()
                    | idTxt.getText().isEmpty() | emailTxt.getText().isEmpty() |
                    nicTxt.getText().isEmpty());
            deleteBtn.setDisable(!isAddressValidate | nameTxt.getText().isEmpty() | contactTxt.getText().isEmpty()
                    | idTxt.getText().isEmpty() | emailTxt.getText().isEmpty() |
                    nicTxt.getText().isEmpty());


        if (isAddressValidate) {
            adressIcon.setVisible(true);

        }else{
            adressIcon.setVisible(false);


        }


    }

    @FXML
    void contactKeyTyped(KeyEvent event) {
        isPhoneNumberValidate= DataValidateController.contactCheck(contactTxt.getText());

            saveBtn.setDisable(!isPhoneNumberValidate | nameTxt.getText().isEmpty() | addressTxt.getText().isEmpty()
                    | idTxt.getText().isEmpty() | emailTxt.getText().isEmpty() |
                    nicTxt.getText().isEmpty());
            updateBtn.setDisable(!isPhoneNumberValidate | nameTxt.getText().isEmpty() | addressTxt.getText().isEmpty()
                    | idTxt.getText().isEmpty() | emailTxt.getText().isEmpty() |
                    nicTxt.getText().isEmpty());
            deleteBtn.setDisable(!isPhoneNumberValidate | nameTxt.getText().isEmpty() | addressTxt.getText().isEmpty()
                    | idTxt.getText().isEmpty() | emailTxt.getText().isEmpty() |
                    nicTxt.getText().isEmpty());
        if (isPhoneNumberValidate){
            contactIcon.setVisible(true);
        }else {
            contactIcon.setVisible(false);

        }



    }


    @FXML
    void emailKeyTyped(KeyEvent event) {
        isEmailValidate = DataValidateController.emailCheck(emailTxt.getText());

            saveBtn.setDisable(!isEmailValidate | nameTxt.getText().isEmpty() | addressTxt.getText().isEmpty()
                    | idTxt.getText().isEmpty() | contactTxt.getText().isEmpty() |
                    nicTxt.getText().isEmpty());
            updateBtn.setDisable(!isEmailValidate | nameTxt.getText().isEmpty() | addressTxt.getText().isEmpty()
                    | idTxt.getText().isEmpty() | contactTxt.getText().isEmpty() |
                    nicTxt.getText().isEmpty());
            deleteBtn.setDisable(!isEmailValidate | nameTxt.getText().isEmpty() | addressTxt.getText().isEmpty()
                    | idTxt.getText().isEmpty() | contactTxt.getText().isEmpty() |
                    nicTxt.getText().isEmpty());

        if (isEmailValidate){
            emailIcon.setVisible(true);
        }else{
            emailIcon.setVisible(false);

        }



    }

    @FXML
    void empIdKeyTyped(KeyEvent event) {
        isEmpValidate= DataValidateController.empIdValidate(idTxt.getText());

            saveBtn.setDisable(!isEmpValidate | nameTxt.getText().isEmpty() | addressTxt.getText().isEmpty()
                    | emailTxt.getText().isEmpty() | contactTxt.getText().isEmpty() |
                    nicTxt.getText().isEmpty());
            updateBtn.setDisable(!isEmpValidate | nameTxt.getText().isEmpty() | addressTxt.getText().isEmpty()
                    | emailTxt.getText().isEmpty() | contactTxt.getText().isEmpty() |
                    nicTxt.getText().isEmpty());
            deleteBtn.setDisable(!isEmpValidate | nameTxt.getText().isEmpty() | addressTxt.getText().isEmpty()
                    | emailTxt.getText().isEmpty() | contactTxt.getText().isEmpty() |
                    nicTxt.getText().isEmpty());


        if (isEmpValidate){
            empIdIcon.setVisible(true);
        }else {
            empIdIcon.setVisible(false);
        }



    }

    @FXML
    void empNameKeyTyped(KeyEvent event) {
        isNameValidate= DataValidateController.customerNameValidate(nameTxt.getText());

            saveBtn.setDisable(!isNameValidate | idTxt.getText().isEmpty() | addressTxt.getText().isEmpty()
                    | emailTxt.getText().isEmpty() | contactTxt.getText().isEmpty() |
                    nicTxt.getText().isEmpty());
            updateBtn.setDisable(!isNameValidate | idTxt.getText().isEmpty() | addressTxt.getText().isEmpty()
                    | emailTxt.getText().isEmpty() | contactTxt.getText().isEmpty() |
                    nicTxt.getText().isEmpty());
            deleteBtn.setDisable(!isNameValidate | idTxt.getText().isEmpty() | addressTxt.getText().isEmpty()
                    | emailTxt.getText().isEmpty() | contactTxt.getText().isEmpty() |
                    nicTxt.getText().isEmpty());

        if (isNameValidate){
            empNameIcon.setVisible(true);
        }else{
            empNameIcon.setVisible(false);

        }



    }


    @FXML
    void nicKeyTyped(KeyEvent event) {
       isNicValidate = DataValidateController.nicValidate(nicTxt.getText());
            saveBtn.setDisable(!isNicValidate | idTxt.getText().isEmpty() | addressTxt.getText().isEmpty()
                   | emailTxt.getText().isEmpty() | contactTxt.getText().isEmpty() |
                   nameTxt.getText().isEmpty());
           updateBtn.setDisable(!isNicValidate | idTxt.getText().isEmpty() | addressTxt.getText().isEmpty()
                   | emailTxt.getText().isEmpty() | contactTxt.getText().isEmpty() |
                   nameTxt.getText().isEmpty());
           deleteBtn.setDisable(!isNicValidate | idTxt.getText().isEmpty() | addressTxt.getText().isEmpty()
                   | emailTxt.getText().isEmpty() | contactTxt.getText().isEmpty() |
                   nameTxt.getText().isEmpty());
       if (isNicValidate){
           nicIcon.setVisible(true);
       }else{
           nicIcon.setVisible(false);

       }



    }

    @FXML
    void jobTitleOnAction(ActionEvent event) {
        jobTitleIcon.setVisible(!jobTitileTxt.getSelectionModel().isEmpty());

    }

    public void dobOnAction(ActionEvent actionEvent) {
        dobIcon.setVisible(dobTxt.getValue() != null);
    }

    @FXML
    void initialize() {
        saveBtn.setDisable(true);
        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);
        txtfieldbordercolor(idTxt);
        txtfieldbordercolor(nameTxt);
        txtfieldbordercolor(addressTxt);
        txtfieldbordercolor(contactTxt);
        txtfieldbordercolor(emailTxt);
        txtfieldbordercolor(nicTxt);
        getCellValueFactory();
        getAll();
        jobTitileTxt.getItems().addAll("Admin", "Cashier");

    }

    public void dobKeyTyped(KeyEvent keyEvent) {


    }
}
