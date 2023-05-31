package lk.ijse.cafe_au_lait.model;

import lk.ijse.cafe_au_lait.db.DBConnection;
import lk.ijse.cafe_au_lait.dto.Delivery;
import lk.ijse.cafe_au_lait.dto.Order;
import lk.ijse.cafe_au_lait.view.tdm.CartTM;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PlaceOrderModel {

    static Delivery gotnewdelivery;

    public static void sendObject(Delivery newDelivery) {
        gotnewdelivery = newDelivery;
    }

    public static boolean placeOrder(String oId, String customerId, Double orderPayment, CartTM cartTM, List<Order> orderDtoList) {
        Connection con = null;

        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = OrderModel.save(oId, customerId, orderPayment, LocalDate.now(), LocalTime.now(), cartTM);
            if (isSaved) {
//                boolean isUpdated = ItemModel.updateQty(orderDtoList);
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
            }

        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }
        return false;
    }

    public static boolean saveDeliver(Delivery newDeliverDto) throws SQLException {

        String Sql = "INSERT INTO delivery(deliveryId,deliveryLocation,orderId,empId) VALUES(?,?,?,?)";

        return CrudUtil.execute(Sql,
                newDeliverDto.getDeliverId(),
                newDeliverDto.getLocation(),
                newDeliverDto.getOrderId(),
                newDeliverDto.getEmpId());

    }
}
