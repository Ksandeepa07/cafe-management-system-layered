package lk.ijse.cafe_au_lait.dao.custom.impl;

import javafx.scene.image.Image;
import lk.ijse.cafe_au_lait.dao.custom.EventImageDAO;
import lk.ijse.cafe_au_lait.entity.EventImage;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventImageDAOImpl implements EventImageDAO {
    @Override
    public boolean save(EventImage eventImage) throws SQLException {
        return CrudUtil.execute("INSERT INTO eventImages(eventId,eventImage)VALUES(?,?)",
                eventImage.getEventId(),eventImage.getEventImage());
    }

    @Override
    public ArrayList<EventImage> getAll() throws SQLException {
        return null;
    }

    @Override
    public EventImage searchById(String s) throws SQLException {
        return null;
    }

    @Override
    public boolean update(EventImage eventImage) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return false;
    }


    @Override
    public List<Image> getImage() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT eventImage FROM eventImages");
        List<Image> data = new ArrayList<>();
        while (resultSet.next()) {
            byte[]imageData=resultSet.getBytes(1);
            Image image=new Image(new ByteArrayInputStream(imageData));
            data.add(image);
        }
        return data;
    }
}
