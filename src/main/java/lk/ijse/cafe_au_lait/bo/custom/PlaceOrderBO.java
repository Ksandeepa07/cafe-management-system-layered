package lk.ijse.cafe_au_lait.bo.custom;

import lk.ijse.cafe_au_lait.bo.SuperBO;
import lk.ijse.cafe_au_lait.dto.CustomerDTO;
import lk.ijse.cafe_au_lait.dto.DeliveryDTO;
import lk.ijse.cafe_au_lait.dto.ItemDTO;
import lk.ijse.cafe_au_lait.dto.OrdersDTO;
import lk.ijse.cafe_au_lait.view.tdm.CartTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {
    ArrayList<String> loadCustId();

    ArrayList<String> loadItemId();

    ItemDTO searchBItemyId(String id) throws SQLException;

    boolean placeOrder(OrdersDTO ordersDTO) throws SQLException;

     void saveDelivery(DeliveryDTO newDeliverDto);

    ArrayList<String> loadEmpIds() throws SQLException;

    String generateNextOrderId() throws SQLException;

    CustomerDTO searchCustomerById(String value) throws SQLException;
}
