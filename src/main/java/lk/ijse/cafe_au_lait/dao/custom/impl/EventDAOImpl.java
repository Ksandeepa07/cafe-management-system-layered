package lk.ijse.cafe_au_lait.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfxtras.test.AssertNode;
import lk.ijse.cafe_au_lait.dao.custom.EventDAO;
import lk.ijse.cafe_au_lait.dto.EventDTO;
import lk.ijse.cafe_au_lait.entity.Event;
import lk.ijse.cafe_au_lait.util.CrudUtil;
import lk.ijse.cafe_au_lait.util.NotificationController;
import lk.ijse.cafe_au_lait.view.tdm.EventTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class EventDAOImpl implements EventDAO {
    @Override
    public boolean save(Event event) throws SQLException {
        try {
            return CrudUtil.execute("INSERT INTO Event (eventId,empId,eventName,eventType,eventDate,eventTime)" +
                            "VALUES(?,?,?,?,?,?)",
                    event.getEventId(),
                    event.getEmpId(),
                    event.getEventName(),
                    event.getEventType(),
                    event.getEventDate(),
                    event.getEventTime());
        } catch (SQLIntegrityConstraintViolationException throwables) {
            NotificationController.ErrorMasseage("This Event Id is Already Exsits");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Event> getAll() throws SQLException {
        ArrayList<Event> eventData = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = CrudUtil.execute("SELECT * FROM Event");
            while (resultSet.next()) {
                eventData.add(new Event(
                        resultSet.getString(2),
                        resultSet.getString(1),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)

                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return eventData;
    }

    @Override
    public Event searchById(String id) throws SQLException {
        ResultSet resultSet = null;
        try {
            resultSet = CrudUtil.execute("SELECT * FROM Event WHERE eventId=?", id);
            if (resultSet.next()) {
                return new Event(
                        resultSet.getString(2),
                        resultSet.getString(1),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)

                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    @Override
    public boolean update(Event event) throws SQLException {
        try {
            return CrudUtil.execute("UPDATE Event SET empId=?,eventName=?,eventType=?,eventDate=?,eventTime=?" +
                            "WHERE eventId=? ",

                    event.getEmpId(),
                    event.getEventName(),
                    event.getEventType(),
                    event.getEventDate(),
                    event.getEventTime(),
                    event.getEventId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        try {
            return CrudUtil.execute("DELETE FROM Event WHERE eventId=?", id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException {
        ResultSet resultSet=CrudUtil.execute("SELECT * FROM Event");
        ArrayList<String> eventData=new ArrayList<>();
        while (resultSet.next()){
            eventData.add(
                    resultSet.getString(1)
            );
        }
        return eventData;
    }
    }

