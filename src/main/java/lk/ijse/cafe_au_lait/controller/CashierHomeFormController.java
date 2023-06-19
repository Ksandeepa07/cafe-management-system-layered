package lk.ijse.cafe_au_lait.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.cafe_au_lait.bo.BOFactory;
import lk.ijse.cafe_au_lait.bo.custom.HomeBO;

import lk.ijse.cafe_au_lait.controller.util.StageController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CashierHomeFormController {

    private static final int UPDATE_INTERVAL = 5000; // in milliseconds
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private AnchorPane ancPane;
    @FXML
    private PieChart pieChart;
    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private ImageView image;

    private Timeline timeline;

    private int currentIndex;

    @FXML
    private Label customerCountLbl;

    @FXML
    private Label orderCountLbl;

    @FXML
    private Label todayIncomeLbl;

    @FXML
    private Label dataLbl;

    HomeBO homeBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.HOME);

    void setPieChart() {

        try {
            ObservableList<PieChart.Data> obList = FXCollections.observableArrayList();
            ArrayList<PieChart.Data> data =homeBO.getPieChartData();
            for (PieChart.Data datum : data) {
                obList.add(datum);
            }
            pieChart.getData().addAll(obList);
            pieChart.setTitle("Most Trending Products");
            pieChart.setStartAngle(180);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    void lineChart() {
        lineChart.setTitle("Income Chart 2023");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");



        try {
            XYChart.Series series  = homeBO.getLineChartData();
            series.setName("Income Chart");
            lineChart.getData().add(series);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




    }

    public void eventAnimation() {

        try {
//            dataLbl.setStyle("-fx-border-radius: 20px");
            List<Image> imageData  = homeBO.getEventImage();
            System.out.println(imageData);
            Image image = imageData.get(currentIndex);
            ImageView imageView = new ImageView(image);
            dataLbl.setGraphic((imageView));

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
                currentIndex = (currentIndex + 1) % imageData.size();
                Image newImage = imageData.get(currentIndex);
                ImageView imageView1 = new ImageView(newImage);
                dataLbl.setGraphic((imageView1));
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void eventLblClicked(MouseEvent event) {
//        EventDTO eventDTO1 = EventModel.searchById(dataLbl.getText());
        StageController.changeStage("/view/cashierEvent.fxml", "Event");

    }
//    public void save(){
//        File file = new File("E:\\Cafe au lait project\\proct photos\\icons8-mail-48.png");
//        try {
//            byte[] imageData = Files.readAllBytes(file.toPath());
//            boolean isSaved= false;
//            try {
//                isSaved = EventModel.SaveImage(imageData);
//            } catch (Exception throwables) {
//                System.out.println(throwables);
//            }
//            if(isSaved){
//                System.out.println("saved");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    void countcustomer(){
        try {
            int count= homeBO.countCustomerId();
            System.out.println(count);
            customerCountLbl.setText(String.valueOf("0"+count));

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    void countOrders(){
        try {
            int count=homeBO.countOrdersId();
            orderCountLbl.setText(String.valueOf("0"+count));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void countTodayIncome(){
        try {
            int count=homeBO.countTodayIncome();
            if (count==0){
                todayIncomeLbl.setText("0"+String.valueOf(count));
            }else{
                todayIncomeLbl.setText(String.valueOf(count));

            }
         } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @FXML
    void initialize() {
//        save();
        countTodayIncome();
        countcustomer();
        countOrders();
        eventAnimation();
        setPieChart();
        lineChart();


    }

}
