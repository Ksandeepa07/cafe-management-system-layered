package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe_au_lait.dto.User;
import lk.ijse.cafe_au_lait.model.UserModel;
import lk.ijse.cafe_au_lait.util.AnimationController;
import lk.ijse.cafe_au_lait.util.DataValidateController;
import lk.ijse.cafe_au_lait.util.NotificationController;
import lk.ijse.cafe_au_lait.util.StageController;
import org.mockito.internal.matchers.Not;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.ResourceBundle;

public class SignUpController {

    @FXML
    public AnchorPane ancPane;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField usernameTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private TextField emailTxt;


    @FXML
    private ComboBox<?> chooseOption;

    @FXML
    private JFXButton signBtn;

    @FXML
    private Hyperlink alreadyHaveAnAccount;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private ComboBox jobTitlte;


    public void alreadyHaveAnAccountClick(ActionEvent actionEvent) throws IOException {
        AnimationController.fadeUpAnimation("/view/loginPage.fxml", ancPane);
    }

    public void signClick(ActionEvent actionEvent) {

        if(jobTitlte.getSelectionModel().isEmpty()|emailTxt.getText().isEmpty()|passwordTxt.getText().isEmpty()|confirmPassword.getText().isEmpty()|usernameTxt.getText().isEmpty()){
            NotificationController.ErrorMasseage("Please fill all empty fields before sign up for a account");
        }else if (!DataValidateController.customerNameValidate(usernameTxt.getText())|
        !DataValidateController.emailCheck(emailTxt.getText())){
            NotificationController.ErrorMasseage("Invalid type password,email or username.");


//        }else if (){
//
        }
        else {
            String username = usernameTxt.getText();
            String password = passwordTxt.getText();
            String email = emailTxt.getText();
            String jobTitle = (String) jobTitlte.getValue();

            User user = new User(username, password, email, jobTitle);

            if (passwordTxt.getText().equals(confirmPassword.getText())) {
                boolean isSaved = false;
                try {
                    isSaved = UserModel.save(user);
                    if (isSaved) {
                        signBtn.getScene().getWindow().hide();
                        StageController.changeStage("/view/loginPage.fxml","login");
                        emailTxt.setText("");
                        usernameTxt.setText("");
                        passwordTxt.setText("");
                        confirmPassword.setText("");
                        NotificationController.animationMesseage("/assets/tick.gif", "sign up", "" +
                                "Sign Up Sucessfull !");
                    }
                }
                catch (Exception throwables) {
                    throwables.printStackTrace();
                }

            } else {
                NotificationController.ErrorMasseage("Password dont match.Please check your password!!");
            }

        }

    }

    @FXML
    void passwordKeyTyped(KeyEvent event) {

    }

    @FXML
    void usernameKeyTypeds(KeyEvent event) {
//        boolean isValidate=DataValidateController.customerNameValidate(usernameTxt.getText());
//        signBtn.setDisable(!isValidate|passwordTxt.getText().isEmpty()|emailTxt.getText().isEmpty());

    }

    @FXML
    void emailKeyTyped(KeyEvent event) {
//        boolean isValidate=DataValidateController.empIdValidate(emailTxt.getText());
//        signBtn.setDisable(!isValidate|passwordTxt.getText().isEmpty()|usernameTxt.getText().isEmpty());

    }


    @FXML
    void initialize() {
//        signBtn.setDisable(true);
        jobTitlte.getItems().addAll("Admin", "Cashier");

    }


}
