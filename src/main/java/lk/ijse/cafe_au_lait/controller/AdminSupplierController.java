package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.cafe_au_lait.bo.BOFactory;
import lk.ijse.cafe_au_lait.bo.custom.SupplierBO;
import lk.ijse.cafe_au_lait.dto.SupplierDTO;
import lk.ijse.cafe_au_lait.view.tdm.SupplierTM;
import lk.ijse.cafe_au_lait.controller.util.DataValidateController;
import lk.ijse.cafe_au_lait.controller.util.NotificationController;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AdminSupplierController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField contactTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colJobTitle;

    @FXML
    private TextField emailTxt;

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
    private Label emailCheckLbl;

    @FXML
    private Label contactCheckLb;

    @FXML
    private ImageView adressIcon;

    @FXML
    private ImageView contactIcon;

    @FXML
    private ImageView spplierNameIcon;

    @FXML
    private ImageView supplierIdIcon;

    @FXML
    private ImageView emailIcon;

    @FXML
    private Tooltip supplierIdToolTip;

    SupplierBO supplierBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);


    @FXML
    void deleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted = supplierBO.deleteSupplier(idTxt.getText());
            boolean result = NotificationController.confirmationMasseage("Are you sure you want remove this " +
                    "employee ?");
            if (result) {

                if (isDeleted) {
                    deleteBtn.setDisable(true);
                    updateBtn.setDisable(true);
                    saveBtn.setDisable(true);
                    supplierIdIcon.setVisible(false);
                    spplierNameIcon.setVisible(false);
                    adressIcon.setVisible(false);
                    emailIcon.setVisible(false);
                    contactIcon.setVisible(false);
                    idTxt.setText(" ");
                    nameTxt.setText(" ");
                    addressTxt.setText(" ");
                    contactTxt.setText(" ");
                    emailTxt.setText(" ");
                    getAll();
                    NotificationController.animationMesseage("/assets/images/tick.gif", "Delete",
                            "Supplier Deleted sucessfully !!");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void saveOnAction(ActionEvent event) {
        String id = idTxt.getText();
        String name = nameTxt.getText();
        String contact = contactTxt.getText();
        String address = addressTxt.getText();
        String email = emailTxt.getText();


                    SupplierDTO supplierDTO = new SupplierDTO(id, name, contact, address, email);
                    try {
                        boolean isSaved = supplierBO.saveSupplier(supplierDTO);
                        if (isSaved) {
                            deleteBtn.setDisable(true);
                            updateBtn.setDisable(true);
                            saveBtn.setDisable(true);
                            idTxt.setText("");
                            nameTxt.setText("");
                            addressTxt.setText("");
                            contactTxt.setText("");
                            emailTxt.setText("");
                            supplierIdIcon.setVisible(false);
                            spplierNameIcon.setVisible(false);
                            adressIcon.setVisible(false);
                            emailIcon.setVisible(false);
                            contactIcon.setVisible(false);
                            getAll();
                            NotificationController.animationMesseage("/assets/images/tick.gif", "Saved",
                                    "Supplier Added sucessfully !!");

                        }
                    } catch (SQLIntegrityConstraintViolationException throwables) {
                        NotificationController.ErrorMasseage("This Supplier Id is Already Exsits");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

    }

    @FXML
    void searchIconClick(MouseEvent event) {
        try {
            SupplierDTO supplierDTO = supplierBO.searchSuppplierById(searchIdTxt.getText());
            if (supplierDTO != null) {
                idTxt.setText(supplierDTO.getId());
                nameTxt.setText(supplierDTO.getName());
                contactTxt.setText(supplierDTO.getContact());
                addressTxt.setText(supplierDTO.getAddress());
                emailTxt.setText(supplierDTO.getEmail());
            } else {
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void searchTable(KeyEvent event) throws SQLException {
        String searchValue = searchIdTxt.getText().trim();
        ArrayList<SupplierDTO> load = supplierBO.getAllSuppliers();
        ObservableList<SupplierTM> obList= FXCollections.observableArrayList();
        for (SupplierDTO supplierDTO : load) {
            obList.add(new SupplierTM(supplierDTO.getId(),supplierDTO.getName(),supplierDTO.getContact(),supplierDTO.getAddress(),supplierDTO.getEmail()));
        }

        if (!searchValue.isEmpty()) {
            ObservableList<SupplierTM> filteredData = obList.filtered(new Predicate<SupplierTM>() {
                @Override
                public boolean test(SupplierTM supplier) {
                    return String.valueOf(supplier.getId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblSupplier.setItems(filteredData);
        } else {
            tblSupplier.setItems(obList);
        }


    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = idTxt.getText();
        String name = nameTxt.getText();
        String contact = contactTxt.getText();
        String address = addressTxt.getText();
        String email = emailTxt.getText();

                    SupplierDTO supplierDTO = new SupplierDTO(id, name, contact, address, email);
                    boolean result = NotificationController.confirmationMasseage("Are you sure you want update this " +
                            "employee ?");
                    if (result) {
                        try {
                            boolean isUpdated = supplierBO.updateSupplier(supplierDTO);
                            if (isUpdated) {
                                deleteBtn.setDisable(true);
                                updateBtn.setDisable(true);
                                saveBtn.setDisable(true);
                                idTxt.setText("");
                                nameTxt.setText("");
                                addressTxt.setText("");
                                contactTxt.setText("");
                                emailTxt.setText("");
                                supplierIdIcon.setVisible(false);
                                spplierNameIcon.setVisible(false);
                                adressIcon.setVisible(false);
                                emailIcon.setVisible(false);
                                contactIcon.setVisible(false);

                                getAll();
                                NotificationController.animationMesseage("/assets/images/tick.gif", "Update",
                                        "Suplier Updated sucessfully !!");
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }

    }

    void getAll() {
        tblSupplier.getItems().clear();
        try {
            ArrayList<SupplierDTO> supplierData = supplierBO.getAllSuppliers();
            for (SupplierDTO supplierDatum : supplierData) {
                tblSupplier.getItems().add(new SupplierTM(supplierDatum.getId(),supplierDatum.getName(),supplierDatum.getContact(),supplierDatum.getAddress(),supplierDatum.getEmail()));
            }
//            tblSupplier.setItems(supplierData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void getCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    public void tblClick(MouseEvent mouseEvent) {
        supplierIdIcon.setVisible(true);
        spplierNameIcon.setVisible(true);
        adressIcon.setVisible(true);
        emailIcon.setVisible(true);
        contactIcon.setVisible(true);

        saveBtn.setDisable(false);
        updateBtn.setDisable(false);
        deleteBtn.setDisable(false);
         TablePosition pos = tblSupplier.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<SupplierTM, ?>> columns = tblSupplier.getColumns();

        idTxt.setText(columns.get(0).getCellData(row).toString());
        nameTxt.setText(columns.get(1).getCellData(row).toString());
        contactTxt.setText(columns.get(2).getCellData(row).toString());
        addressTxt.setText(columns.get(3).getCellData(row).toString());
        emailTxt.setText(columns.get(4).getCellData(row).toString());
    }

    @FXML
    void addressKeyTyped(KeyEvent event) {
        boolean isValidate= DataValidateController.addressValidate(addressTxt.getText());
        saveBtn.setDisable(!isValidate|emailTxt.getText().isEmpty()|idTxt.getText().isEmpty()|contactTxt.getText().isEmpty()|
                nameTxt.getText().isEmpty());
        updateBtn.setDisable(!isValidate|emailTxt.getText().isEmpty()|idTxt.getText().isEmpty()|contactTxt.getText().isEmpty()|
                nameTxt.getText().isEmpty());
        deleteBtn.setDisable(!isValidate|emailTxt.getText().isEmpty()|idTxt.getText().isEmpty()|contactTxt.getText().isEmpty()|
                nameTxt.getText().isEmpty());
        if (isValidate){
            addressTxt.setOnAction((e) -> {
                emailTxt.requestFocus();
            });
            adressIcon.setVisible(true);
        }else {
            adressIcon.setVisible(false);
        }

    }

    @FXML
    void contactKeyTyped(KeyEvent event) {
        boolean isValidate= DataValidateController.contactCheck(contactTxt.getText());
        saveBtn.setDisable(!isValidate|addressTxt.getText().isEmpty()|idTxt.getText().isEmpty()|emailTxt.getText().isEmpty()|
                nameTxt.getText().isEmpty());
        updateBtn.setDisable(!isValidate|addressTxt.getText().isEmpty()|idTxt.getText().isEmpty()|emailTxt.getText().isEmpty()|
                nameTxt.getText().isEmpty());
        deleteBtn.setDisable(!isValidate|addressTxt.getText().isEmpty()|idTxt.getText().isEmpty()|emailTxt.getText().isEmpty()|
                nameTxt.getText().isEmpty());
        if (isValidate){
            contactTxt.setOnAction((e) -> {
                addressTxt.requestFocus();
            });
            contactIcon.setVisible(true);
        }else {
            contactIcon.setVisible(false);
        }

    }
    @FXML
    void emailKeyTYped(KeyEvent event) {
        boolean isValidate= DataValidateController.emailCheck(emailTxt.getText());
        saveBtn.setDisable(!isValidate|addressTxt.getText().isEmpty()|idTxt.getText().isEmpty()|contactTxt.getText().isEmpty()|
                nameTxt.getText().isEmpty());
        updateBtn.setDisable(!isValidate|addressTxt.getText().isEmpty()|idTxt.getText().isEmpty()|contactTxt.getText().isEmpty()|
                nameTxt.getText().isEmpty());
        deleteBtn.setDisable(!isValidate|addressTxt.getText().isEmpty()|idTxt.getText().isEmpty()|contactTxt.getText().isEmpty()|
                nameTxt.getText().isEmpty());
        if (isValidate){

            emailIcon.setVisible(true);
        }else {
            emailIcon.setVisible(false);
        }

    }

    @FXML
    void idKeyTyped(KeyEvent event) {
        idTxt.setTooltip(supplierIdToolTip);
       boolean isValidate= DataValidateController.supplierIdValidate(idTxt.getText());
       saveBtn.setDisable(!isValidate|addressTxt.getText().isEmpty()|emailTxt.getText().isEmpty()|contactTxt.getText().isEmpty()|
               nameTxt.getText().isEmpty());
        updateBtn.setDisable(!isValidate|addressTxt.getText().isEmpty()|emailTxt.getText().isEmpty()|contactTxt.getText().isEmpty()|
                nameTxt.getText().isEmpty());
        deleteBtn.setDisable(!isValidate|addressTxt.getText().isEmpty()|emailTxt.getText().isEmpty()|contactTxt.getText().isEmpty()|
                nameTxt.getText().isEmpty());
        if (isValidate){
            idTxt.setOnAction((e) -> {
                nameTxt.requestFocus();
            });
            supplierIdIcon.setVisible(true);
        }else {
            supplierIdIcon.setVisible(false);
        }

    }

    @FXML
    void nameKeyTyped(KeyEvent event) {
        boolean isValidate= DataValidateController.customerNameValidate(nameTxt.getText());
        saveBtn.setDisable(!isValidate|addressTxt.getText().isEmpty()|emailTxt.getText().isEmpty()|contactTxt.getText().isEmpty()|
                idTxt.getText().isEmpty());
        updateBtn.setDisable(!isValidate|addressTxt.getText().isEmpty()|emailTxt.getText().isEmpty()|contactTxt.getText().isEmpty()|
                idTxt.getText().isEmpty());
        deleteBtn.setDisable(!isValidate|addressTxt.getText().isEmpty()|emailTxt.getText().isEmpty()|contactTxt.getText().isEmpty()|
                idTxt.getText().isEmpty());

        if (isValidate){
            nameTxt.setOnAction((e) -> {
                contactTxt.requestFocus();
            });
            spplierNameIcon.setVisible(true);
        }else {
            spplierNameIcon.setVisible(false);
        }

    }


    @FXML
    void initialize() {
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);
        saveBtn.setDisable(true);
        getAll();
        getCellValueFactory();
    }
}
