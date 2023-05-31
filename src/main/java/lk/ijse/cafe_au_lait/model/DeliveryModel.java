package lk.ijse.cafe_au_lait.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cafe_au_lait.dto.Delivery;
import lk.ijse.cafe_au_lait.view.tdm.DeliveryTM;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryModel {

    public static ObservableList<DeliveryTM> getAll() throws SQLException {
        String sql = "SELECT * FROM delivery";
        ResultSet resultSet = CrudUtil.execute(sql);
        ObservableList<DeliveryTM> deliveryData = FXCollections.observableArrayList();

        while (resultSet.next()) {
            deliveryData.add(new DeliveryTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            ));


        }
        return deliveryData;
    }


    public static boolean deleteById(String text) throws SQLException {
        String sql = "DELETE FROM delivery WHERE deliveryId=?";
        return CrudUtil.execute(sql, text);
    }

    public static boolean update(Delivery newDeliverDto) throws SQLException {
        String sql = "UPDATE delivery SET deliveryLocation=?, orderId=?,empId=? WHERE deliveryId=? ";
        return CrudUtil.execute(sql,
                newDeliverDto.getLocation(),
                newDeliverDto.getOrderId(),
                newDeliverDto.getEmpId(),
                newDeliverDto.getDeliverId());
    }

    public static Delivery searchByDeliveryId(String text) throws SQLException {
        String sql = "SELECT * FROM delivery WHERE deliveryId=?";
        ResultSet resultSet = CrudUtil.execute(sql, text);
        if (resultSet.next()) {
            return new Delivery(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }
}



