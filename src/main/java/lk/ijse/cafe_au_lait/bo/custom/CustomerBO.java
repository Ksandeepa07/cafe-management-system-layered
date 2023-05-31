package lk.ijse.cafe_au_lait.bo.custom;

import lk.ijse.cafe_au_lait.bo.SuperBO;
import lk.ijse.cafe_au_lait.dto.CustomerDTO;
import lk.ijse.cafe_au_lait.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO<T,ID> extends SuperBO {
    boolean save(T t) throws SQLException;
    ArrayList<T> getAll() throws SQLException;
    T searchById(ID id);

    boolean update(T t) throws SQLException ;
    boolean delete(ID id) throws SQLException ;

    ArrayList<ID> loadCustId() ;


    int countId() throws SQLException ;
}
