package lk.ijse.cafe_au_lait.dao.custom.impl;

import lk.ijse.cafe_au_lait.dao.custom.OrdersDAO;
import lk.ijse.cafe_au_lait.entity.Orders;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersDAOImpl implements OrdersDAO {
    @Override
    public boolean save(Orders orders) throws SQLException {


        try {
            return CrudUtil.execute("INSERT INTO Orders(orderId,custId,orderDate,orderTime,orderPayment,Delivery)VALUES(?," +
                            "?,?,?,?,?)",
                    orders.getOrderId(),
                    orders.getCustId(),
                    orders.getOrderDate(),
                    orders.getOrderTime(),
                    orders.getOrderPayment(),
                    orders.getDelivery()

            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException {
        return null;
    }

    @Override
    public Orders searchById(String s) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Orders orders) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return false;
    }
}
