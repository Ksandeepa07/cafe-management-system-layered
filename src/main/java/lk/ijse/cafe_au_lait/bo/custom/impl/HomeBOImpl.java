package lk.ijse.cafe_au_lait.bo.custom.impl;

import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import lk.ijse.cafe_au_lait.bo.custom.HomeBO;
import lk.ijse.cafe_au_lait.dao.DAOFactory;
import lk.ijse.cafe_au_lait.dao.custom.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeBOImpl implements HomeBO {
    CustomerDAO customerDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    OrdersDAO ordersDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERS);
    OrderDetailDAO orderDetailDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);
    EventImageDAO eventImageDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EVENTIMAGE);
    @Override
    public int countCustomerId() throws SQLException {
        return customerDAO.countIds();
    }

    @Override
    public int countOrdersId() throws SQLException {
        return ordersDAO.countOrders();
    }

    @Override
    public int countTodayIncome() throws SQLException {
        return ordersDAO.countIncome();
    }

    @Override
    public ArrayList<PieChart.Data> getPieChartData() throws SQLException {
        return orderDetailDAO.setData();
    }

    @Override
    public XYChart.Series getLineChartData() throws SQLException {
        return ordersDAO.getdata();
    }

    @Override
    public List<Image> getEventImage() throws SQLException {
        return eventImageDAO.getImage();
    }
}
