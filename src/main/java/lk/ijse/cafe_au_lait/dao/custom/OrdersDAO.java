package lk.ijse.cafe_au_lait.dao.custom;

import lk.ijse.cafe_au_lait.dao.CrudDAO;
import lk.ijse.cafe_au_lait.entity.Orders;

import java.sql.SQLException;

public interface OrdersDAO extends CrudDAO<Orders,String> {
    String generateNextOrderId() throws SQLException;
}
