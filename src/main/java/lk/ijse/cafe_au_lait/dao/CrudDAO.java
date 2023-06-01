package lk.ijse.cafe_au_lait.dao;

import lk.ijse.cafe_au_lait.dto.CustomerDTO;
import lk.ijse.cafe_au_lait.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID> extends SuperDAO {
   boolean save(T t) throws SQLException;
   ArrayList<T> getAll() throws SQLException;
   T searchById(ID id) throws SQLException;

     boolean update(T t) throws SQLException ;
     boolean delete(ID id) throws SQLException ;

//    ArrayList<ID> loadIds() ;

//    int countId() throws SQLException ;
}
