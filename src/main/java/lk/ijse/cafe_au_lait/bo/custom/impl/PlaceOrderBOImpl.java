package lk.ijse.cafe_au_lait.bo.custom.impl;

import lk.ijse.cafe_au_lait.bo.custom.PlaceOrderBO;
import lk.ijse.cafe_au_lait.dao.DAOFactory;
import lk.ijse.cafe_au_lait.dao.custom.CustomerDAO;
import lk.ijse.cafe_au_lait.dao.custom.ItemDAO;
import lk.ijse.cafe_au_lait.dao.custom.OrderDetailDAO;
import lk.ijse.cafe_au_lait.dao.custom.OrdersDAO;
import lk.ijse.cafe_au_lait.db.DBConnection;
import lk.ijse.cafe_au_lait.dto.ItemDTO;
import lk.ijse.cafe_au_lait.dto.OrderDetailDTO;
import lk.ijse.cafe_au_lait.dto.OrdersDTO;
import lk.ijse.cafe_au_lait.entity.Item;
import lk.ijse.cafe_au_lait.entity.OrdeDetail;
import lk.ijse.cafe_au_lait.entity.Orders;
import lk.ijse.cafe_au_lait.model.OrderModel;
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

            con.commit();
            return true;

//                if (isUpdated) {
//                    boolean isplaced = OrderDetailModel.save(oId, orderDtoList);
//                    if (isplaced) {
//                        if (cartTM.getDelivery().equals("Yes")) {
//                            boolean isdeliverd = saveDeliver(gotnewdelivery);
//                            if (isdeliverd) {
//                                con.commit();
//                                return true;
//                            }
//                        } else {
//                            con.commit();
//                            return true;
//                        }
//                    }
//                }


        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }
        return false;
    }

    private ItemDTO findItemByID(String id) throws SQLException {
        Item item=itemDAO.searchById(id);
        return new ItemDTO(item.getId(),item.getName(),item.getQuantity(),item.getPrice(),item.getCategory());

    }
}
