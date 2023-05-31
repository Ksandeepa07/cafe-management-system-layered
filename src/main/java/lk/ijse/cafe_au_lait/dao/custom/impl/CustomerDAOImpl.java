package lk.ijse.cafe_au_lait.dao.custom.impl;

import lk.ijse.cafe_au_lait.dao.custom.CustomerDAO;
import lk.ijse.cafe_au_lait.entity.Customer;
import lk.ijse.cafe_au_lait.util.CrudUtil;
import lk.ijse.cafe_au_lait.util.NotificationController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer customer) {
        try {
            return CrudUtil.execute("INSERT INTO Customer (custId,custName,phoneNumber,email)" +
                            "VALUES(?,?,?,?)",
                    customer.getCustId(),
                    customer.getCustName(),
                    customer.getCustContact(),
                    customer.getCustEmail());
        } catch (SQLIntegrityConstraintViolationException throwables) {
            NotificationController.ErrorMasseage("This Customer Id is Already Exsits");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    @Override
    public ArrayList<Customer> getAll() throws SQLException {
//        String sql = "SELECT * FROM customer";

        ArrayList <Customer>customerData=new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer");

        while (resultSet.next()) {
            customerData.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            ));
        }
        return customerData;
    }

    @Override
    public Customer searchById(String id) {
        ResultSet resultSet = null;
        try {
            resultSet = CrudUtil.execute("SELECT * FROM Customer WHERE custId=?", id);
            if (resultSet.next()) {
                return new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
//        String sql = "UPDATE customer SET custName=?,phoneNumber=?,email=?" +
//                "WHERE custId=? ";

        return CrudUtil.execute("UPDATE Customer SET custName=?,phoneNumber=?,email=?" +
                        "WHERE custId=? ",

                customer.getCustName(),
                customer.getCustContact(),
                customer.getCustEmail(),
                customer.getCustId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM Customer WHERE custId=?", id);
    }

    @Override
    public ArrayList<String> loadIds() {
        return null;
    }

    @Override
    public int countId() throws SQLException {
        return 0;
    }
}
