package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe_au_lait.db.DBConnection;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class AdminReportsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private JFXButton customerDetailsBtn;

    @FXML
    private JFXButton ordersDetailsBtn;

    @FXML
    private JFXButton supplierLoadDetailsBtn;

    @FXML
    private JFXButton suppliersDetailBtn;

    @FXML
    void customerDetailsBtnClick(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/reports/customerReports.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void ordersDetailsBtnClick(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/reports/ordersReprts.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void suplliersDetailsBtnClick(ActionEvent event) {


    }

    @FXML
    void supplierLoadDetailsBtnClick(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/reports/supplierLoadReports.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }

}
