package lk.ijse.cafe_au_lait.dao.custom.impl;

import lk.ijse.cafe_au_lait.dao.custom.OrderDetailDAO;
import lk.ijse.cafe_au_lait.entity.OrdeDetail;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean save(OrdeDetail ordeDetail) throws SQLException {
        try {
            return CrudUtil.execute("INSERT INTO OrderDetail (orderId,itemId,orderQuantity)VALUES(?,?,?)",
                    ordeDetail.getOrderId(),
                    ordeDetail.getItemId(),
                    ordeDetail.getOrderQuantity());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<OrdeDetail> getAll() throws SQLException {
        return null;
    }

    @Override
    public OrdeDetail searchById(String s) throws SQLException {
        return null;
    }

    @Override
    public boolean update(OrdeDetail ordeDetail) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return false;
    }
}
