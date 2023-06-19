package lk.ijse.cafe_au_lait.dao.custom.impl;

import javafx.scene.chart.XYChart;
import lk.ijse.cafe_au_lait.dao.custom.OrdersDAO;
import lk.ijse.cafe_au_lait.entity.Orders;
import lk.ijse.cafe_au_lait.controller.util.CrudUtil;

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
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Orders");
        ArrayList<Orders> ordersData =new ArrayList<>();

        while (resultSet.next()) {
            ordersData.add(new Orders(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3).toLocalDate(),
                    resultSet.getTime(4).toLocalTime(),
                    resultSet.getDouble(5),
                    resultSet.getString(6)

            ));

        }
        return ordersData;
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

    @Override
    public int countOrders() throws SQLException {
        ResultSet resultSet=CrudUtil.execute("SELECT COUNT(ORDERiD) FROM Orders where orderdate=DATE(NOW())");
        int count=0;
        while (resultSet.next()){
            count=resultSet.getInt(1);
        }
        return count;
    }

    @Override
    public int countIncome() throws SQLException {

        ResultSet resultSet=CrudUtil.execute("SELECT SUM(ORDERPAYMENT) FROM Orders WHERE ORDERDATE=DATE(NOW())");
        int count=0;
        while (resultSet.next()){
            count=resultSet.getInt(1);
        }
        return count;
    }

    @Override
    public XYChart.Series getdata() throws SQLException {
        ResultSet resultSet=CrudUtil.execute("SELECT MONTHNAME(orderDate),sum(orderPayment) from Orders group by MONTHNAME(orderDate)");
        XYChart.Series series=new XYChart.Series();
        while (resultSet.next()){
            series.getData().add(new XYChart.Data(resultSet.getString(1),resultSet.getInt(2)));
        }
        return series;
    }

    @Override
    public boolean updateDeliveryModeOnOrders(String oId, String message) throws SQLException {
        return CrudUtil.execute("UPDATE Orders SET delivery=? WHERE orderId=?",
                message, oId);
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Orders");
        ArrayList<String> orderIds =new ArrayList<>();

        while (resultSet.next()) {
            orderIds.add(
                    resultSet.getString(1)
            );

        }
        return orderIds;
    }

    @Override
    public int countOrdersOnDay(String date) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(orderId) FROM Orders WHERE orderDate=?",
                date);

        if (resultSet.next()) {
            int count = Integer.parseInt(resultSet.getString(1));
            return count;
        }
        return Integer.parseInt(null);
    }

    @Override
    public int countOrdersPlaceByCustomer(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(orderId) from Orders where custId=?", id);
        if (resultSet.next()) {
            int idd = Integer.parseInt(resultSet.getString(1));
            return idd;
        }
        return Integer.parseInt(null);
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
