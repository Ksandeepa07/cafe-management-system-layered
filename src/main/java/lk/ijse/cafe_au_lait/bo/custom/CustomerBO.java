package lk.ijse.cafe_au_lait.bo.custom;

import lk.ijse.cafe_au_lait.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO<T, ID> extends SuperBO {
    boolean saveCustomer(T t) throws SQLException;

    boolean updateCustomer(T t) throws SQLException;

    boolean deleteCustomer(ID id) throws SQLException;

    ArrayList<T> getAllCustomers() throws SQLException;

    T searchCustomerById(ID id) throws SQLException;

    ArrayList<ID> loadCustId();

    int countId() throws SQLException;
}
