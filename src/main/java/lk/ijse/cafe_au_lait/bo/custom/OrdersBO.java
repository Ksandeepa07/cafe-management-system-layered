package lk.ijse.cafe_au_lait.bo.custom;

import lk.ijse.cafe_au_lait.bo.SuperBO;
import lk.ijse.cafe_au_lait.dto.CustomerDTO;
import lk.ijse.cafe_au_lait.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdersBO extends SuperBO {
    ArrayList<OrdersDTO> getAllOrders() throws SQLException;

    int countOrdersOnDay(String text) throws SQLException;

    CustomerDTO searchCustomerById(String text) throws SQLException;

    int countOrdersPlaceBYCustomer(String text) throws SQLException;
}
