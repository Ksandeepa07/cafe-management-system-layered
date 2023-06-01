package lk.ijse.cafe_au_lait.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import lk.ijse.cafe_au_lait.dto.EventDTO;
import lk.ijse.cafe_au_lait.view.tdm.EventTM;
import lk.ijse.cafe_au_lait.util.CrudUtil;
import lk.ijse.cafe_au_lait.util.NotificationController;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class EventModel {
    private static String[] data;
    private static final int currentIndex = 0;

//    public static boolean save(EventDTO eventDTO1) {
//        String sql = "INSERT INTO event (eventId,empId,eventName,eventType,eventDate,eventTime)" +
//                "VALUES(?,?,?,?,?,?)";
//
//        try {
//            return CrudUtil.execute(sql,
//                    eventDTO1.getEventId(),
//                    eventDTO1.getEmpId(),
//                    eventDTO1.getEventName(),
//                    eventDTO1.getEventType(),
//                    eventDTO1.getEventDate(),
//                    eventDTO1.getEventTime());
//        } catch (SQLIntegrityConstraintViolationException throwables) {
//            NotificationController.ErrorMasseage("This Event Id is Already Exsits");
//        }
//        catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return false;
//    }

//    public static EventDTO searchById(String text) {
//        String sql = "SELECT * FROM Event WHERE eventId=?";
//        ResultSet resultSet = null;
//        try {
//            resultSet = CrudUtil.execute(sql, text);
//            if (resultSet.next()) {
//                return new EventDTO(
//                        resultSet.getString(2),
//                        resultSet.getString(1),
//                        resultSet.getString(3),
//                        resultSet.getString(4),
//                        resultSet.getString(5),
//                        resultSet.getString(6)
//
//                );
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//
//        return null;
//
//    }


//    public static ObservableList<EventTM> getAll() {
//        String sql = "SELECT * FROM Event";
//
//        ObservableList<EventTM> eventData = FXCollections.observableArrayList();
//        ResultSet resultSet = null;
//        try {
//            resultSet = CrudUtil.execute(sql);
//            while (resultSet.next()) {
//                eventData.add(new EventTM(
//                        resultSet.getString(2),
//                        resultSet.getString(1),
//                        resultSet.getString(3),
//                        resultSet.getString(4),
//                        resultSet.getString(5),
//                        resultSet.getString(6)
//
//                ));
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//
//        return eventData;
//    }

//    public static boolean update(EventDTO eventDTO1) {
//        String sql = "UPDATE Event SET empId=?,eventName=?,eventType=?,eventDate=?,eventTime=?" +
//                "WHERE eventId=? ";
//        try {
//            return CrudUtil.execute(sql,
//
//                    eventDTO1.getEmpId(),
//                    eventDTO1.getEventName(),
//                    eventDTO1.getEventType(),
//                    eventDTO1.getEventDate(),
//                    eventDTO1.getEventTime(),
//                    eventDTO1.getEventId());
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return false;
//
//    }

//    public static boolean delete(String text) {
//        String sql = "DELETE FROM Event WHERE eventId=?";
//        try {
//            return CrudUtil.execute(sql, text);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return false;
//    }

    public static List<Image> eventData() throws SQLException {
//        String sql = "SELECT eventName FROM event";
//        ResultSet resultSet = CrudUtil.execute(sql);
//        List<String> data = new ArrayList<>();
//        while (resultSet.next()) {
//            data.add(
//                    resultSet.getString(1)
//            );
//        }
//
//
//        return data;
        String sql = "SELECT eventImage FROM eventImages";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<Image> data = new ArrayList<>();
        while (resultSet.next()) {
           byte[]imageData=resultSet.getBytes(1);
           Image image=new Image(new ByteArrayInputStream(imageData));
           data.add(image);
        }
        return data;
    }

//    public static boolean SaveImage(byte[] imageData) throws SQLException {
//        String sql="insert into image(filePath)values(?)";
//        return CrudUtil.execute(sql,imageData);
//
//    }

//    public static boolean saveImage(String eventId, InputStream filePath) throws SQLException {
//        String sql="INSERT INTO eventImages(eventId,eventImage)VALUES(?,?)";
//        return CrudUtil.execute(sql,
//                eventId,filePath);
//    }

//    public static ObservableList<String> loadEventIds() throws SQLException {
//        String sql="SELECT * FROM Event";
//        ResultSet resultSet=CrudUtil.execute(sql);
//        ObservableList<String>eventData=FXCollections.observableArrayList();
//        while (resultSet.next()){
//            eventData.add(
//                    resultSet.getString(1)
//            );
//        }
//        return eventData;
//    }
}


