package lk.ijse.cafe_au_lait.dao.custom.impl;

import javafx.scene.chart.PieChart;
import lk.ijse.cafe_au_lait.dao.DAOFactory;
import lk.ijse.cafe_au_lait.dao.custom.ItemDAO;
import lk.ijse.cafe_au_lait.dao.custom.OrderDetailDAO;
import lk.ijse.cafe_au_lait.entity.Item;
import lk.ijse.cafe_au_lait.entity.OrdeDetail;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    ItemDAO itemDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
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

    @Override
    public ArrayList<PieChart.Data> setData() throws SQLException {
        ArrayList<PieChart.Data> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("select itemId,SUM(orderQuantity)as orderCount from OrderDetail group by itemId order by ordercount desc limit 5");
        while (resultSet.next()) {
            Item item = itemDAO.searchById(resultSet.getString(1));
            data.add(
                    new PieChart.Data(item.getName(), resultSet.getInt(2))
            );
        }
        return data;
    }
}
