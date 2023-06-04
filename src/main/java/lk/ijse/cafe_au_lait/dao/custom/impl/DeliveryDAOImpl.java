package lk.ijse.cafe_au_lait.dao.custom.impl;

import lk.ijse.cafe_au_lait.dao.custom.DeliveryDAO;
import lk.ijse.cafe_au_lait.entity.Delivery;
import lk.ijse.cafe_au_lait.util.CrudUtil;

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
        return null;
    }

    @Override
    public Delivery searchById(String s) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Delivery delivery) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return false;
    }
}
