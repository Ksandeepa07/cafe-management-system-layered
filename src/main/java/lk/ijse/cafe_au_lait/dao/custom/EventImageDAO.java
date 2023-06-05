package lk.ijse.cafe_au_lait.dao.custom;

import javafx.scene.image.Image;
import lk.ijse.cafe_au_lait.dao.CrudDAO;
import lk.ijse.cafe_au_lait.entity.EventImage;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public interface EventImageDAO extends CrudDAO<EventImage,String> {
    List<Image> getImage() throws SQLException;
}
