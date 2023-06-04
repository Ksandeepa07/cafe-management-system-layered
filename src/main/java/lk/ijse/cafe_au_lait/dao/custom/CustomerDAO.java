package lk.ijse.cafe_au_lait.dao.custom;

import lk.ijse.cafe_au_lait.dao.CrudDAO;
import lk.ijse.cafe_au_lait.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    ArrayList<String> loadIds();
    int countIds() throws SQLException;
}
