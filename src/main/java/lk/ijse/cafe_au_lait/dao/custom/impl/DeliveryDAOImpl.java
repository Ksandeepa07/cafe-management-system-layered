package lk.ijse.cafe_au_lait.dao.custom.impl;

import lk.ijse.cafe_au_lait.dao.custom.DeliveryDAO;
import lk.ijse.cafe_au_lait.entity.Delivery;
import lk.ijse.cafe_au_lait.controller.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryDAOImpl implements DeliveryDAO {
    @Override
    public boolean save(Delivery delivery)  {
        System.out.println(delivery.getDeliverId());
        try {
            return CrudUtil.execute("INSERT INTO Delivery(deliveryId,deliveryLocation,orderId,empId) VALUES(?,?,?,?)",
                    delivery.getDeliverId(),
                    delivery.getDeliveryLocation(),
                    delivery.getOrderId(),
                    delivery.getEmpId());
        } catch (Exception throwables) {
            System.out.println(throwables);
        }
        return false;
    }

    @Override
    public ArrayList<Delivery> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Delivery");
        ArrayList<Delivery> deliveryData =new ArrayList<>();

        while (resultSet.next()) {
            deliveryData.add(new Delivery(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            ));


        }
        return deliveryData;
    }

    @Override
    public Delivery searchById(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Delivery WHERE deliveryId=?", id);
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

    @Override
    public boolean update(Delivery delivery) throws SQLException {
        return CrudUtil.execute("UPDATE Delivery SET deliveryLocation=?, orderId=?,empId=? WHERE deliveryId=? ",
                delivery.getDeliveryLocation(),
                delivery.getOrderId(),
                delivery.getEmpId(),
                delivery.getDeliverId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM Delivery WHERE deliveryId=?", id);
    }
}
