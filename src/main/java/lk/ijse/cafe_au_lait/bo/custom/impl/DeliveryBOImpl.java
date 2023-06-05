package lk.ijse.cafe_au_lait.bo.custom.impl;

import lk.ijse.cafe_au_lait.bo.custom.DeliveryBO;
import lk.ijse.cafe_au_lait.dao.DAOFactory;
import lk.ijse.cafe_au_lait.dao.custom.DeliveryDAO;
import lk.ijse.cafe_au_lait.dao.custom.EmployeeDAO;
import lk.ijse.cafe_au_lait.dao.custom.OrdersDAO;
import lk.ijse.cafe_au_lait.dto.DeliveryDTO;
import lk.ijse.cafe_au_lait.entity.Delivery;

import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryBOImpl implements DeliveryBO {
    DeliveryDAO deliveryDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DELIVERY);
    OrdersDAO ordersDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERS);
    EmployeeDAO employeeDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    ArrayList<DeliveryDTO> deliveryDTOS=new ArrayList<>();

    @Override
    public ArrayList<DeliveryDTO> getAllDelivery() throws SQLException {
        ArrayList<Delivery> load=deliveryDAO.getAll();
        for (Delivery delivery : load) {
            deliveryDTOS.add(new DeliveryDTO(delivery.getDeliverId(),delivery.getDeliveryLocation(),delivery.getOrderId(),delivery.getEmpId()));
        }
        return deliveryDTOS;
    }

    @Override
    public DeliveryDTO searchByDeliveryId(String id) throws SQLException {
        Delivery delivery=deliveryDAO.searchById(id);
        return new DeliveryDTO(delivery.getDeliverId(),delivery.getDeliveryLocation(),delivery.getOrderId(),delivery.getEmpId());
    }

    @Override
    public boolean deleteDeliveryById(String id) throws SQLException {
        return deliveryDAO.delete(id);
    }

    @Override
    public boolean updateDelivery(DeliveryDTO newDeliverDto) throws SQLException {
        return deliveryDAO.update(new Delivery(newDeliverDto.getDeliverId(),newDeliverDto.getLocation(),newDeliverDto.getOrderId(),newDeliverDto.getEmpId()));
    }

    @Override
    public boolean updateDeliveyMode(String value, String message) throws SQLException {
        return ordersDAO.updateDeliveryModeOnOrders(value,message);
    }

    @Override
    public ArrayList<String> loadOrderIds() throws SQLException {
        return ordersDAO.loadIds();
    }

    @Override
    public ArrayList<String> loadEmpIds() throws SQLException {
        return employeeDAO.loadIds();
    }
}
