package lk.ijse.cafe_au_lait.dao.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.cafe_au_lait.dao.CrudDAO;
import lk.ijse.cafe_au_lait.entity.Orders;

import java.sql.SQLException;

public interface OrdersDAO extends CrudDAO<Orders,String> {
    String generateNextOrderId() throws SQLException;

    int countOrders() throws SQLException;

    int countIncome() throws SQLException;

    XYChart.Series getdata() throws SQLException;
}
