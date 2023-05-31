package lk.ijse.cafe_au_lait.bo.custom;

import lk.ijse.cafe_au_lait.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO<T, ID> extends SuperBO {
    boolean saveItem(T t) throws SQLException;

    boolean deleteItem(ID id) throws SQLException;

    boolean updateItem(T t) throws SQLException;

    ArrayList<T> getAllItem() throws SQLException;

    T searchItemById(ID id) throws SQLException;
}
