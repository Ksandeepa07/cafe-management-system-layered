package lk.ijse.cafe_au_lait.dao.custom;

import lk.ijse.cafe_au_lait.dao.CrudDAO;
import lk.ijse.cafe_au_lait.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO extends CrudDAO<Supplier,String> {
    ArrayList<String> loadIds() throws SQLException;
    String generateNextId() throws SQLException;
}
