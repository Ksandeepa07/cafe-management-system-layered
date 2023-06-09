package lk.ijse.cafe_au_lait.util;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.cafe_au_lait.bo.BOFactory;
import lk.ijse.cafe_au_lait.bo.custom.ForgotPasswordBO;
import lk.ijse.cafe_au_lait.dto.UserDTO;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

public class ForgotPasswordController {

    String usernamee;
    String email;
    String jobTitle;
    int otp;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private JFXButton sendCodeBtn;
    @FXML
    private TextField otpTxt;
    @FXML
    private JFXButton submitButton;
    @FXML
    private Label otpLbl;
    @FXML
    private Label EmailTxt;
    @FXML
    private TextField Emailtxtt;
    @FXML
    private AnchorPane recoveryAnchorpane;
    @FXML
    private JFXButton submitButton1;
    @FXML
    private Label countLbl;
    @FXML
    private TextField usernametxt;

    @FXML
    private Label usernameLbl;

    ForgotPasswordBO forgotPasswordBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.FORGOTPASSWORD);


    @FXML
    void sendCodeClick(ActionEvent event) throws Exception {

        String username = usernametxt.getText();
        UserDTO userDTO = forgotPasswordBO.SearchUserById(username);
        email = userDTO.getEmial();
        usernamee = userDTO.getUsername();
        jobTitle=userDTO.getJobTitle();
        if (DataValidateController.emailCheck(Emailtxtt.getText())) {
            if (Emailtxtt.getText().equals(email)) {
                try {
                    countLbl.setVisible(true);
                    submitButton.setVisible(true);
                    otpLbl.setVisible(true);
                    otpTxt.setVisible(true);


                    Random random = new Random();
                    otp = random.nextInt(9000);
                    otp += 1000;
                    EmailController.sendEmail(Emailtxtt.getText(), "cafe au lait verification", otp + "");
                    NotificationController.animationMesseage("/assets/images/tik.png", "otp", "OTP sent " +
                            "sucessfully");
                    AnimationController.updateTime(sendCodeBtn, countLbl);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

            } else {
                NotificationController.ErrorMasseage("Invalid e-mail address for username " + usernamee);
            }
        } else {
            NotificationController.ErrorMasseage("Invalid e-mail format");
            submitButton.setVisible(false);
            otpLbl.setVisible(false);
            otpTxt.setVisible(false);
        }

    }

    @FXML
    void submmitCodeClick(ActionEvent event) throws IOException {
        if (String.valueOf(otp).equals(otpTxt.getText())) {
            EmailTxt.setText("Enter your new password");
            otpLbl.setText("Confirm new Password");
            usernametxt.setVisible(false);
            sendCodeBtn.setVisible(false);
            submitButton.setVisible(false);
            submitButton1.setVisible(true);

            usernameLbl.setVisible(false);
            countLbl.setVisible(false);
            otpTxt.setText("");
            Emailtxtt.setText("");

        } else {
            NotificationController.ErrorMasseage("Wrong OTP !");
            submitButton.setText("submit");

        }
    }

    @FXML
    void submmitCodeClick1(ActionEvent event) throws IOException {
        String password = Emailtxtt.getText();
        try {

            boolean isSavd = forgotPasswordBO.updateUserPassword(new UserDTO(usernamee,password,email,jobTitle));
            if (isSavd) {
                NotificationController.animationMesseage("/assets/images/tik.png", "OTP",
                        "Password change sucessfully");
                recoveryAnchorpane.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginPage.fxml"));
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("ABC");
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                stage.show();

            } else {
                NotificationController.ErrorMasseage("Wrong OTP !! try again");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @FXML
    void initialize() {
        countLbl.setVisible(false);
        submitButton1.setVisible(false);
        submitButton.setVisible(false);
        otpLbl.setVisible(false);
        otpTxt.setVisible(false);
        assert sendCodeBtn != null : "fx:id=\"sendCodeBtn\" was not injected: check your FXML file 'forgotPassword.fxml'.";
        assert otpTxt != null : "fx:id=\"otpTxt\" was not injected: check your FXML file 'forgotPassword.fxml'.";
        assert submitButton != null : "fx:id=\"submitButton\" was not injected: check your FXML file 'forgotPassword.fxml'.";
        assert otpLbl != null : "fx:id=\"otpLbl\" was not injected: check your FXML file 'forgotPassword.fxml'.";
        assert EmailTxt != null : "fx:id=\"EmailTxt\" was not injected: check your FXML file 'forgotPassword.fxml'.";

    }
}
