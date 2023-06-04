package lk.ijse.cafe_au_lait.dao.custom.impl;

import lk.ijse.cafe_au_lait.dao.custom.OrdersDAO;
import lk.ijse.cafe_au_lait.entity.Orders;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.ResultSet;
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

    @Override
    public String generateNextOrderId() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT orderId FROM Orders ORDER BY orderId DESC LIMIT 1");
        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("OD-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit = String.format("%03d", id);
            return "OD-" + digit;

        }
        return "OD-001";
    }
}
