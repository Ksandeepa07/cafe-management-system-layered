package lk.ijse.cafe_au_lait.dao.custom;

import lk.ijse.cafe_au_lait.dao.CrudDAO;
import lk.ijse.cafe_au_lait.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    int countIds() throws SQLException;
}
