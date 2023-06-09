package lk.ijse.cafe_au_lait.bo.custom.impl;

import lk.ijse.cafe_au_lait.bo.custom.PlaceOrderBO;
import lk.ijse.cafe_au_lait.dao.DAOFactory;
import lk.ijse.cafe_au_lait.dao.custom.*;
import lk.ijse.cafe_au_lait.db.DBConnection;
import lk.ijse.cafe_au_lait.dto.*;
import lk.ijse.cafe_au_lait.entity.*;
import lk.ijse.cafe_au_lait.view.tdm.CartTM;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    CustomerDAO customerDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
    OrdersDAO ordersDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERS);
    OrderDetailDAO orderDetailDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);
    DeliveryDAO deliveryDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DELIVERY);
    EmployeeDAO employeeDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    static DeliveryDTO deliveryDTO;
    @Override
    public ArrayList<String> loadCustId() {
        return customerDAO.loadIds();
    }

    @Override
    public ArrayList<String> loadItemId() {
        return itemDAO.loadIds();
    }

    @Override
    public ItemDTO searchBItemyId(String id) throws SQLException {
        Item item=itemDAO.searchById(id);
        return new ItemDTO(item.getId(),item.getName(),item.getQuantity(),item.getPrice(),item.getCategory());
    }

    @Override
    public boolean placeOrder(OrdersDTO ordersDTO) throws SQLException {
        Connection con = null;

        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            //insert query to orders table
            Orders orders=new Orders(ordersDTO.getOrderId(),ordersDTO.getCustId(),ordersDTO.getOrderDate(),ordersDTO.getOrderTime(),ordersDTO.getOrderPayment(),
                    ordersDTO.getDelivery());

            boolean isOrderSaved = ordersDAO.save(orders);
            if (!isOrderSaved) {
                con.rollback();
                con.setAutoCommit(true);
                return false;
            }

            //update query to item table and insert query to orderdetils table

            for(OrderDetailDTO orderDetailDTO: ordersDTO.getOrderDetaisList()){
                OrdeDetail ordeDetail=new OrdeDetail(ordersDTO.getOrderId(),orderDetailDTO.getItemId(),orderDetailDTO.getOrderQuantity());
                boolean isOrderDetailSaved=orderDetailDAO.save(ordeDetail);

                if (!isOrderDetailSaved) {
                    con.rollback();
                    con.setAutoCommit(true);
                    return false;
                }


                ItemDTO item=findItemByID(orderDetailDTO.getItemId());;
                int quantity=item.getQuantity()-orderDetailDTO.getOrderQuantity();
                boolean isItemUpdated = itemDAO.update(new Item(item.getId(),item.getName(),quantity,item.getPrice(),item.getCategory()));
//
                if(!isItemUpdated) {
                    con.rollback();
                    con.setAutoCommit(true);
                    return false;
                }
                System.out.println("saved");


            }

            //delivery insert

            if (ordersDTO.getDelivery().equals("Yes")) {
                System.out.println(deliveryDTO.getLocation());
                boolean isdeliverd =deliveryDAO.save(new Delivery(deliveryDTO.getDeliverId(),deliveryDTO.getLocation(),deliveryDTO.getOrderId(),deliveryDTO.getEmpId()));
                if (!isdeliverd) {
                    con.rollback();
                    con.setAutoCommit(true);
                    return false;
                }
            }else {
                con.commit();
                return true;
            }

            con.commit();
            return true;

        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }
        return false;
    }

    @Override
    public void saveDelivery(DeliveryDTO newDeliverDto) {
        this.deliveryDTO=newDeliverDto;
        System.out.println(deliveryDTO.getDeliverId());
    }

    @Override
    public ArrayList<String> loadEmpIds() throws SQLException {
        return employeeDAO.loadIds();
    }

    @Override
    public String generateNextOrderId() throws SQLException {
        return ordersDAO.generateNextOrderId();
    }

    @Override
    public CustomerDTO searchCustomerById(String id) throws SQLException {
        Customer customer=customerDAO.searchById(id);
        return new CustomerDTO(customer.getCustId(),customer.getCustName(),customer.getCustContact(),customer.getCustEmail());
    }


    private ItemDTO findItemByID(String id) throws SQLException {
        Item item=itemDAO.searchById(id);
        return new ItemDTO(item.getId(),item.getName(),item.getQuantity(),item.getPrice(),item.getCategory());

    }
}
