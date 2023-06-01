package lk.ijse.cafe_au_lait.dao.custom;

import lk.ijse.cafe_au_lait.dao.CrudDAO;
import lk.ijse.cafe_au_lait.entity.Event;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EventDAO extends CrudDAO<Event,String> {
    ArrayList<String> loadIds() throws SQLException;
}
