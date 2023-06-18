package lk.ijse.cafe_au_lait.bo.custom.impl;

import lk.ijse.cafe_au_lait.bo.custom.OrdersBO;
import lk.ijse.cafe_au_lait.dao.DAOFactory;
import lk.ijse.cafe_au_lait.dao.custom.CustomerDAO;
import lk.ijse.cafe_au_lait.dao.custom.OrdersDAO;
import lk.ijse.cafe_au_lait.dto.CustomerDTO;
import lk.ijse.cafe_au_lait.dto.OrdersDTO;
import lk.ijse.cafe_au_lait.entity.Customer;
import lk.ijse.cafe_au_lait.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersBOImpl implements OrdersBO {
    OrdersDAO ordersDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERS);
    CustomerDAO customerDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<OrdersDTO> getAllOrders() throws SQLException {
        ArrayList<OrdersDTO> ordersDTOS=new ArrayList<>();
        ArrayList<Orders> load=ordersDAO.getAll();
        for (Orders orders : load) {
            ordersDTOS.add(new OrdersDTO(orders.getOrderId(),orders.getCustId(),orders.getOrderDate(),orders.getOrderTime(),orders.getOrderPayment(),orders.getDelivery()));
        }
        return ordersDTOS;
    }

    @Override
    public int countOrdersOnDay(String date) throws SQLException {
        return ordersDAO.countOrdersOnDay(date);
    }

    @Override
    public CustomerDTO searchCustomerById(String id) throws SQLException {
        Customer customer=customerDAO.searchById(id);
        return new CustomerDTO(customer.getCustId(),customer.getCustName(),customer.getCustContact(),customer.getCustEmail());
    }

    @Override
    public int countOrdersPlaceBYCustomer(String id) throws SQLException {
        return ordersDAO.countOrdersPlaceByCustomer(id);
    }
}

