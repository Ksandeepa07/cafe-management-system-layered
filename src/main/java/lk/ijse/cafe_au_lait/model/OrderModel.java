package lk.ijse.cafe_au_lait.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lk.ijse.cafe_au_lait.view.tdm.CartTM;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class OrderModel {
    public static String getNextOrderId() throws SQLException {
        String sql = "SELECT orderId FROM Orders ORDER BY orderId DESC LIMIT 1";


        ResultSet resultSet = CrudUtil.execute(sql);
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

//
//    public static boolean save(String oId, String customerId, Double orderPayment, LocalDate now, LocalTime now1, List<OrderDto> orderDtoList) {
//        for(OrderDto orderDto:orderDtoList){
//            if(!save(oId,customerId,orderPayment,now,now1,orderDto)){
//                return false;
//            }
//        }
//return true;
//    }

//    public static boolean save(String oId, String customerId, Double orderPayment, LocalDate now, LocalTime now1, CartTM cartTM) {
//        String sql = "INSERT INTO Orders(orderId,custId,orderDate,orderTime,orderPayment,Delivery)VALUES(?," +
//                "?,?,?,?,?)";
//
//        try {
//            return CrudUtil.execute(sql,
//                    oId,
//                    customerId,
//                    now,
//                    now1,
//                    orderPayment,
//                    cartTM.getDelivery()
//
//            );
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return false;
//
//    }

    public static boolean updateDeliveyMode(String orderId, String message) throws SQLException {
        String sql = "UPDATE orders SET delivery=? WHERE orderId=?";
        return CrudUtil.execute(sql,
                message, orderId);
    }

    public static ObservableList<String> loadOrderIds() throws SQLException {
        String sql = "SELECT * FROM ORDERS";
        ResultSet resultSet = CrudUtil.execute(sql);
        ObservableList<String> orderIds = FXCollections.observableArrayList();

        while (resultSet.next()) {
            orderIds.add(
                    resultSet.getString(1)
            );

        }
        return orderIds;
    }

    public static XYChart.Series lineChartData() throws SQLException {
        String sql="SELECT MONTHNAME(orderDate),sum(orderPayment) from orders group by MONTHNAME(orderDate)";
        ResultSet resultSet=CrudUtil.execute(sql);
        XYChart.Series series=new XYChart.Series();
        while (resultSet.next()){
            series.getData().add(new XYChart.Data(resultSet.getString(1),resultSet.getInt(2)));
        }
       return series;
        }

    public static int countOrdersId() throws SQLException {
        String sql="SELECT COUNT(ORDERiD) FROM ORDERS where orderdate=DATE(NOW())";
        ResultSet resultSet=CrudUtil.execute(sql);
        int count=0;
        while (resultSet.next()){
            count=resultSet.getInt(1);
        }
        return count;
    }

    public static int countIncome() throws SQLException {
        String sql="SELECT SUM(ORDERPAYMENT) FROM ORDERS WHERE ORDERDATE=DATE(NOW())";
        ResultSet resultSet=CrudUtil.execute(sql);
        int count=0;
        while (resultSet.next()){
            count=resultSet.getInt(1);
        }
        return count;
    }
}

