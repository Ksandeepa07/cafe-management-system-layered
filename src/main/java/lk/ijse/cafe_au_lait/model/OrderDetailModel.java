package lk.ijse.cafe_au_lait.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import lk.ijse.cafe_au_lait.dto.ItemDTO;
import lk.ijse.cafe_au_lait.dto.Order;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailModel {
    public static boolean save(String oId, List<Order> orderDtoList) {
        for (Order orderDto : orderDtoList) {
            if (!save(oId, orderDto)) {
                return false;
            }
        }
        return true;

    }

    public static boolean save(String oId, Order orderDto) {
        String sql = "INSERT INTO orderDetail (orderId,itemId,orderQuantity)VALUES(?,?,?)";
        try {
            return CrudUtil.execute(sql,
                    oId,
                    orderDto.getItemId(),
                    orderDto.getQty());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

//    public static ObservableList<PieChart.Data> getPieChartData() throws SQLException {
//        String sql = "select itemId,SUM(orderQuantity)as orderCount from orderDetail group by itemId order by ordercount desc limit 5";
//        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
//        ResultSet resultSet = CrudUtil.execute(sql);
//        while (resultSet.next()) {
//            ItemDTO itemDTO = ItemModel.searchById(resultSet.getString(1));
//            data.add(
//                    new PieChart.Data(itemDTO.getName(), resultSet.getInt(2))
//            );
//        }
//        return data;
//    }
}
