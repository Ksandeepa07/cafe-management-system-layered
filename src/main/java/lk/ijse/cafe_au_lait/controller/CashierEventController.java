package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import jfxtras.scene.control.LocalTimeTextField;
import lk.ijse.cafe_au_lait.dto.Event;
import lk.ijse.cafe_au_lait.view.tdm.EventTM;
import lk.ijse.cafe_au_lait.model.EmployeeModel;
import lk.ijse.cafe_au_lait.model.EventModel;
import lk.ijse.cafe_au_lait.util.DataValidateController;
import lk.ijse.cafe_au_lait.util.NotificationController;
import lk.ijse.cafe_au_lait.util.StageController;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import static lk.ijse.cafe_au_lait.util.TextFieldBorderController.txtfieldbordercolor;

public class CashierEventController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private TableColumn<?, ?> colEventId;

    @FXML
    private TableColumn<?, ?> colEventName;

    @FXML
    private TableColumn<?, ?> colEventTime;

    @FXML
    private TableColumn<?, ?> colEventType;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> coleventDate;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private DatePicker eventDateTxt;

    @FXML
    private TextField eventIdTxt;

    @FXML
    private TextField eventNameTxt;

    @FXML
    private LocalTimeTextField eventTimeTxt;

    @FXML
    private TextField eventTypeTxt;

    @FXML
    private JFXComboBox<String> idTxt;


    @FXML
    private JFXButton saveBtn;

    @FXML
    private ImageView searchIcon;

    @FXML
    private TextField searchIdTxt;

    @FXML
    private TableView<EventTM> tblEvent;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private JFXButton addImageBtn;

    @FXML
    private ImageView dateIcon;
    @FXML
    private ImageView eventIdIcon;
    @FXML
    private ImageView empIdIcon;

    @FXML
    private ImageView timeIcon;

    @FXML
    private ImageView typeIcon;
    @FXML
    private ImageView nameIcon;

    @FXML
    void deleteOnAction(ActionEvent event) {

        boolean isDeleted = EventModel.delete(eventIdTxt.getText());
        boolean result = NotificationController.confirmationMasseage("Are you sure you want delete this " +
                "event ?");
        if (result) {
            if (isDeleted) {
                saveBtn.setDisable(true);
                updateBtn.setDisable(true);
                deleteBtn.setDisable(true);
                nameIcon.setVisible(false);
                typeIcon.setVisible(false);
                timeIcon.setVisible(false);
                eventIdIcon.setVisible(false);
                getAll();
                NotificationController.animationMesseage("/assets/tick.gif", "Delete",
                        "Event Deleted sucessfully !!");
                idTxt.setValue(null);
                eventIdTxt.setText("");
                eventNameTxt.setText("");
                eventTypeTxt.setText("");
                eventDateTxt.setValue(null);
                eventTimeTxt.setLocalTime(null);

            }
        }


    }

    @FXML
    void saveOnAction(ActionEvent event) throws FileNotFoundException {
        String time= String.valueOf(eventTimeTxt.getLocalTime());
        if (idTxt.getSelectionModel().isEmpty()&eventDateTxt.getEditor().getText().isEmpty()&time.isEmpty()){
            NotificationController.ErrorMasseage("Employee Id and Event date can't be empty");
        }else if (idTxt.getSelectionModel().isEmpty()){
            NotificationController.ErrorMasseage("Employee Id can't be empty");
        }else if (eventDateTxt.getEditor().getText().isEmpty()){
            NotificationController.ErrorMasseage("Event date can't be empty");
        }else if (time.isEmpty()){
            NotificationController.ErrorMasseage("Event time can't be empty");
        }
        else{

            }
            String id = idTxt.getValue();
            String eventId = eventIdTxt.getText();
            String eventName = eventNameTxt.getText();
            String eventType = eventTypeTxt.getText();
            String eventDate = String.valueOf(eventDateTxt.getValue());
            String eventTime = String.valueOf(eventTimeTxt.getLocalTime());

            Event event1 = new Event(id, eventId, eventName, eventType, eventDate, eventTime);
            boolean isSaved = EventModel.save(event1);

            if (isSaved) {
                saveBtn.setDisable(true);
                updateBtn.setDisable(true);
                deleteBtn.setDisable(true);
                nameIcon.setVisible(false);
                typeIcon.setVisible(false);
                timeIcon.setVisible(false);
                eventIdIcon.setVisible(false);
                getAll();
                idTxt.setValue(null);
                eventIdTxt.setText("");
                eventNameTxt.setText("");
                eventTypeTxt.setText("");
                eventDateTxt.setValue(null);
                eventTimeTxt.setLocalTime(null);
                NotificationController.animationMesseage("/assets/tick.gif", "Saved",
                        "Event Added sucessfully !!");
            }

    }

    @FXML
    void searchIconClick(MouseEvent event) {

        Event event1 = EventModel.searchById(searchIdTxt.getText());
        if (event1 != null) {
            idTxt.setValue(event1.getEmpId());
            eventIdTxt.setText(event1.getEventId());
            eventNameTxt.setText(event1.getEventName());
            eventTypeTxt.setText(event1.getEventType());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(event1.getEventDate(), formatter);
            eventDateTxt.setValue(date);
            eventTimeTxt.setLocalTime(LocalTime.parse(event1.getEventTime()));

        } else {
            NotificationController.ErrorMasseage("Event ID Not Found");
        }


    }

    @FXML
    void searchTable(KeyEvent event) throws SQLException {
        String searchValue = searchIdTxt.getText().trim();
        ObservableList<EventTM> obList = EventModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<EventTM> filteredData = obList.filtered(new Predicate<EventTM>() {
                @Override
                public boolean test(EventTM eventTM) {
                    return String.valueOf(eventTM.getEventId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblEvent.setItems(filteredData);
        } else {
            tblEvent.setItems(obList);
        }

    }

    @FXML
    void tblClick(MouseEvent event) {
        saveBtn.setDisable(false);
        updateBtn.setDisable(false);
        deleteBtn.setDisable(false);
        eventIdIcon.setVisible(true);
        typeIcon.setVisible(true);
        timeIcon.setVisible(true);
        nameIcon.setVisible(true);
        try{
            TablePosition pos = tblEvent.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            // Get the data from the selected row
            ObservableList<TableColumn<EventTM, ?>> columns = tblEvent.getColumns();

            idTxt.setValue(columns.get(0).getCellData(row).toString());
            eventIdTxt.setText(columns.get(1).getCellData(row).toString());
            eventNameTxt.setText(columns.get(2).getCellData(row).toString());
            eventTypeTxt.setText(columns.get(3).getCellData(row).toString());
            eventDateTxt.setValue(LocalDate.parse(columns.get(4).getCellData(row).toString()));
            eventTimeTxt.setLocalTime(LocalTime.parse(columns.get(5).getCellData(row).toString()));

        }catch (Exception e){
            saveBtn.setDisable(true);
            updateBtn.setDisable(true);
            deleteBtn.setDisable(true);
            eventIdIcon.setVisible(false);
            typeIcon.setVisible(false);
            timeIcon.setVisible(false);
            nameIcon.setVisible(false);
        }


    }

    @FXML
    void updateOnAction(ActionEvent event) {
        if (idTxt.getSelectionModel().isEmpty()&eventDateTxt.getEditor().getText().isEmpty()){
            NotificationController.ErrorMasseage("Employee Id and Event date can't be empty");
        }else if (idTxt.getSelectionModel().isEmpty()){
            NotificationController.ErrorMasseage("Employee Id can't be empty");
        }else if (eventDateTxt.getEditor().getText().isEmpty()) {
            NotificationController.ErrorMasseage("Event date can't be empty");
        }else {
            String id = idTxt.getValue();
            String eventId = eventIdTxt.getText();
            String eventName = eventNameTxt.getText();
            String eventType = eventTypeTxt.getText();
            String eventDate = String.valueOf(eventDateTxt.getValue());
            String eventTime = String.valueOf(eventTimeTxt.getLocalTime());

            Event event1 = new Event(id, eventId, eventName, eventType, eventDate, eventTime);

            boolean isUpdated = EventModel.update(event1);
            boolean result = NotificationController.confirmationMasseage("Are you sure you want update this " +
                    "event ?");
            if (result) {
                if (isUpdated) {
                    saveBtn.setDisable(true);
                    updateBtn.setDisable(true);
                    deleteBtn.setDisable(true);
                    nameIcon.setVisible(false);
                    typeIcon.setVisible(false);
                    timeIcon.setVisible(false);
                    eventIdIcon.setVisible(false);
                    idTxt.setValue(null);
                    eventIdTxt.setText("");
                    eventNameTxt.setText("");
                    eventTypeTxt.setText("");
                    eventDateTxt.setValue(null);
                    eventTimeTxt.setLocalTime(null);

                    getAll();
                    NotificationController.animationMesseage("/assets/tick.gif", "Update",
                            "Event Updated sucessfully !!");
                }
            }
        }



    }

    void loadEmployeeId() {

        ObservableList<String> eventData = null;
        try {
            eventData = EmployeeModel.loadEmpIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        idTxt.setItems(eventData);

    }

    void getAll() {

        ObservableList<EventTM> eventData = EventModel.getAll();
        tblEvent.setItems(eventData);

    }

    void getCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colEventId.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        colEventName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        colEventType.setCellValueFactory(new PropertyValueFactory<>("eventType"));
        coleventDate.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        colEventTime.setCellValueFactory(new PropertyValueFactory<>("eventTime"));


    }

    @FXML
    void addImageBtnClick(ActionEvent event) {
        StageController.changeStage("/view/eventImageForm.fxml","Event image");


    }

    @FXML
    void eventIdKeyTyped(KeyEvent event) {
        boolean isValidate= DataValidateController.eventIdValidate(eventIdTxt.getText());

        saveBtn.setDisable(!isValidate|eventNameTxt.getText().isEmpty()|eventTypeTxt.getText().isEmpty());
        updateBtn.setDisable(!isValidate|eventNameTxt.getText().isEmpty()|eventTypeTxt.getText().isEmpty());
        deleteBtn.setDisable(!isValidate|eventNameTxt.getText().isEmpty()|eventTypeTxt.getText().isEmpty());
        if (isValidate){
            eventIdIcon.setVisible(true);
        }else {
            eventIdIcon.setVisible(false);
        }

    }

    @FXML
    void eventNameKeyTyped(KeyEvent event) {
        boolean isValidate= DataValidateController.customerNameValidate(eventNameTxt.getText());
        saveBtn.setDisable(!isValidate|eventIdTxt.getText().isEmpty()|eventTypeTxt.getText().isEmpty());
        updateBtn.setDisable(!isValidate|eventIdTxt.getText().isEmpty()|eventTypeTxt.getText().isEmpty());
        deleteBtn.setDisable(!isValidate|eventIdTxt.getText().isEmpty()|eventTypeTxt.getText().isEmpty());
        if (isValidate){
            nameIcon.setVisible(true);
        }else {
            nameIcon.setVisible(false);
        }

    }

    @FXML
    void eventTypeKeyTyped(KeyEvent event) {
        boolean isValidate= DataValidateController.customerNameValidate(eventTypeTxt.getText());
        saveBtn.setDisable(!isValidate|eventIdTxt.getText().isEmpty()|eventNameTxt.getText().isEmpty());
        updateBtn.setDisable(!isValidate|eventIdTxt.getText().isEmpty()|eventNameTxt.getText().isEmpty());
        deleteBtn.setDisable(!isValidate|eventIdTxt.getText().isEmpty()|eventNameTxt.getText().isEmpty());
        if (isValidate){
            typeIcon.setVisible(true);
        }else {
            typeIcon.setVisible(false);
        }

    }

    @FXML
    void eventDateOnAction(ActionEvent event) {
        dateIcon.setVisible(eventDateTxt.getValue()!=null);

    }

    @FXML
    void empIdOnAction(ActionEvent event) {
        empIdIcon.setVisible(!idTxt.getSelectionModel().isEmpty());
    }

    @FXML
    void dateOnAction(MouseEvent event) {
//        String time= String.valueOf(eventTimeTxt.getLocalTime());
//        timeIcon.setVisible(eventTimeTxt.getDateTimeFormatter()!=null);

    }


    @FXML
    void initialize() {
        saveBtn.setDisable(true);
        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);
        getCellValueFactory();
        getAll();
        loadEmployeeId();
        txtfieldbordercolor(eventIdTxt);
        txtfieldbordercolor(eventNameTxt);
        txtfieldbordercolor(eventTypeTxt);


    }

}
