package lk.ijse.cafe_au_lait.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.cafe_au_lait.bo.SuperBO;
import lk.ijse.cafe_au_lait.dto.ItemDTO;
import lk.ijse.cafe_au_lait.view.tdm.ItemTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO <T,ID> extends SuperBO {
    boolean saveItem(T t) throws SQLException;

    boolean deleteItem(ID id) throws SQLException;


    ArrayList<T> getAllItem() throws SQLException;

    boolean updateItem(T t) throws SQLException;

    T searchItemById(ID id);
}
