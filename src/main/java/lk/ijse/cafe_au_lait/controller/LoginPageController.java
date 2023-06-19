package lk.ijse.cafe_au_lait.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.cafe_au_lait.bo.BOFactory;
import lk.ijse.cafe_au_lait.bo.custom.LoginBO;
import lk.ijse.cafe_au_lait.dto.UserDTO;
import lk.ijse.cafe_au_lait.controller.util.AnimationController;
import lk.ijse.cafe_au_lait.controller.util.NotificationController;
import lk.ijse.cafe_au_lait.controller.util.StageController;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController {
    @FXML
    public AnchorPane ancPane;
    String username;
    String selectJob;
    String password;
    String jobTitle;
    String userr;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField usernameTxt;
    @FXML
    private PasswordField passwordTxt;
    @FXML
    private Button loginBtn;
    @FXML
    private ComboBox chooseOption;
    @FXML
    private TextField txtShowPassword;

    @FXML
    private Hyperlink dontHaveAcoount;

    @FXML
    private Button showPasswordBtn;

    @FXML
    private Button hidePasswordBtn;

    @FXML
    private ImageView showPasswordImage;

    @FXML
    private ImageView hidePasswordImage;

    @FXML
    private Label invlidLbl;

    @FXML
    private Label invlidLbl1;

    LoginBO loginBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.LOGIN);


    public void dontHaveClick(ActionEvent actionEvent) throws IOException {
        AnimationController.fadeAnimation("/view/signUp.fxml", ancPane);
    }

    public void forgotPasswordClick(ActionEvent actionEvent) throws IOException {
        loginBtn.getScene().getWindow().hide();
        StageController.changeStage("/view/forgotPassword.fxml", "Password recovery");
    }

    @FXML
    void loginClick() throws IOException, AWTException {
        try{
            if(   chooseOption.getSelectionModel().getSelectedIndex()==-1){
                NotificationController.ErrorMasseage("please choose an option to login");

            }

        }catch (Exception e){

        }

        username = usernameTxt.getText();
        selectJob = (String) chooseOption.getValue();
        System.out.println(selectJob);
        password = null;
        jobTitle = null;
        try {
            UserDTO userDTO = loginBO.SearchUserById(username);
            password = userDTO.getPassword();
            jobTitle = userDTO.getJobTitle();
            userr = userDTO.getUsername();
        } catch (Exception e) {

        }
        try{
            if (passwordTxt.getText().equals(password) && selectJob.equals(jobTitle) && selectJob.equals("Cashier")) {
                NotificationController.animationMesseage("/assets/images/tick.gif", "Login",
                        "Login succesfull !!");
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                    loginBtn.getScene().getWindow().hide();

                    StageController.changeStage("/view/cashierDashboard.fxml", "Dashboard");

                }));
                timeline.play();
            } else if (passwordTxt.getText().equals(password) && selectJob.equals(jobTitle) && selectJob.equals("Admin")) {
                NotificationController.animationMesseage("/assets/images/tick.gif", "Login", "Login " +
                        "succesfull !!");
                NotificationController.notificationBar("login","Login Sucessfull!");

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                    loginBtn.getScene().getWindow().hide();

                    StageController.changeStage("/view/admindashbord.fxml", "Dashboard");

                }));
                timeline.play();

            }
            else if (usernameTxt.getText().isEmpty() && passwordTxt.getText().isEmpty()) {
                invlidLbl.setVisible(true);
                invlidLbl1.setVisible(true);
                invlidLbl.setText("Username can't be empty");
                invlidLbl1.setText("Password can't be empty");
                usernameTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
                passwordTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");

            } else if (usernameTxt.getText().isEmpty()) {
                invlidLbl.setVisible(true);
                invlidLbl1.setVisible(true);
                invlidLbl.setText("Username can't be empty");
                invlidLbl1.setText("");
                usernameTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
                passwordTxt.setStyle("-fx-border-color: #dfa47e; -fx-border-width: 0 0 3 0;");


            } else if (passwordTxt.getText().isEmpty()) {
                invlidLbl.setVisible(true);
                invlidLbl1.setVisible(true);
                invlidLbl.setText("");
                invlidLbl1.setText("Password can't be empty");
                usernameTxt.setStyle("-fx-border-color: #dfa47e; -fx-border-width: 0 0 3 0;");
                passwordTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");


            } else if (!usernameTxt.getText().equals(userr) && !passwordTxt.getText().equals(password)) {
                invlidLbl.setVisible(true);
                invlidLbl1.setVisible(true);
                invlidLbl.setText("");
                invlidLbl1.setText("Wrong password ! ");
                usernameTxt.setStyle("-fx-border-color: #dfa47e; -fx-border-width: 0 0 3 0;");
                passwordTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");

                invlidLbl.setVisible(true);
                invlidLbl1.setVisible(true);
                invlidLbl.setText("Wrong username !");
                invlidLbl1.setText("");
                usernameTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
                passwordTxt.setStyle("-fx-border-color: #dfa47e; -fx-border-width: 0 0 3 0;");

            } else if (!usernameTxt.getText().equals(userr)) {
                invlidLbl.setVisible(true);
                invlidLbl1.setVisible(true);
                invlidLbl.setText("Wrong username !");
                invlidLbl1.setText("");
                usernameTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
                passwordTxt.setStyle("-fx-border-color: #dfa47e; -fx-border-width: 0 0 3 0;");

            } else if (!passwordTxt.getText().equals(password)) {
                invlidLbl.setVisible(true);
                invlidLbl1.setVisible(true);
                invlidLbl.setText("");
                invlidLbl1.setText("Wrong password ! ");
                usernameTxt.setStyle("-fx-border-color: #dfa47e; -fx-border-width: 0 0 3 0;");
                passwordTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");

            } else {
                NotificationController.animationMesseage("/assets/images/error.png", "Error",
                        "Invalid details ");
                invlidLbl.setText("Invalid details");
                invlidLbl1.setText("Invalid details");
                usernameTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
                passwordTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
                invlidLbl.setVisible(true);
                invlidLbl1.setVisible(true);
            }

        }catch (Exception e){

        }



    }


    public void shiwPasswordClick(MouseEvent mouseEvent) {
        password = passwordTxt.getText();
        passwordTxt.setVisible(false);
        showPasswordImage.setVisible(false);
        txtShowPassword.setVisible(true);
        hidePasswordImage.setVisible(true);
        txtShowPassword.setText(password);
    }

    public void hidepasswordClick(MouseEvent mouseEvent) {
        password = txtShowPassword.getText();
        passwordTxt.setVisible(true);
        showPasswordImage.setVisible(true);
        txtShowPassword.setVisible(false);
        hidePasswordImage.setVisible(false);
        passwordTxt.setText(password);

    }

    public void passwordFieldEnter(KeyEvent keyEvent) {
        System.out.println("ok");
//        if (passwordTxt.getText().equals(password) && selectJob.equals(jobTitle)&& selectJob.equals("Admin") ){
//            dontHaveAcoount.setVisible(true);
//        }else{
//            dontHaveAcoount.setVisible(false);
//        }
    }


    @FXML
    void initialize() {
//        dontHaveAcoount.setVisible(false);
        invlidLbl.setVisible(false);
        invlidLbl1.setVisible(false);
        txtShowPassword.setVisible(false);
        hidePasswordImage.setVisible(false);
        chooseOption.getItems().addAll("Admin", "Cashier");

    }


}
