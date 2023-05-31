package lk.ijse.cafe_au_lait.util;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class TextFieldBorderController {
    public static void txtfieldbordercolor(TextField txtfield) {
        txtfield.setStyle("-fx-border-color: #7B3927; -fx-border-width: 0 0 3 0;");
        txtfield.setOnMouseEntered((MouseEvent e) -> {
            txtfield.setStyle("-fx-border-color: #ff0000; -fx-border-width: 0 0 3 0;");
        });
        txtfield.setOnMouseExited((MouseEvent e) -> {
            txtfield.setStyle("-fx-border-color: #7B3927; -fx-border-width: 0 0 3 0;");
        });
    }

    public static void datepickerbordercolor(DatePicker datepicker) {
        datepicker.setStyle("-fx-border-color: #7B3927; -fx-border-width: 0 0 3 0;");
        datepicker.setOnMouseEntered((MouseEvent e) -> {
            datepicker.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
        });
        datepicker.setOnMouseExited((MouseEvent e) -> {
            datepicker.setStyle("-fx-border-color: #7B3927; -fx-border-width: 0 0 3 0;");
        });
    }

    public static void comboBoxbordercolor(ComboBox comboBox) {
        comboBox.setStyle("-fx-border-color: #7B3927; -fx-border-width: 0 0 3 0;");
        comboBox.setOnMouseEntered((MouseEvent e) -> {
            comboBox.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
        });
        comboBox.setOnMouseExited((MouseEvent e) -> {
            comboBox.setStyle("-fx-border-color: #7B3927; -fx-border-width: 0 0 3 0;");
        });
    }
}
