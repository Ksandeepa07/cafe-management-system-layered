package lk.ijse.cafe_au_lait.bo.custom;

import lk.ijse.cafe_au_lait.bo.SuperBO;
import lk.ijse.cafe_au_lait.dto.DeliveryDTO;
import lk.ijse.cafe_au_lait.entity.Delivery;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryBO extends SuperBO {

    ArrayList<DeliveryDTO> getAllDelivery() throws SQLException;

    DeliveryDTO searchByDeliveryId(String text) throws SQLException;

    boolean deleteDeliveryById(String text) throws SQLException;

    boolean updateDelivery(DeliveryDTO newDeliverDto) throws SQLException;

    boolean updateDeliveyMode(String value, String message) throws SQLException;

    ArrayList<String> loadOrderIds();
}
