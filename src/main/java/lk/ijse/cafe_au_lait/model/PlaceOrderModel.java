package lk.ijse.cafe_au_lait.model;

import lk.ijse.cafe_au_lait.dto.DeliveryDTO;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.SQLException;

public class PlaceOrderModel {

    static DeliveryDTO gotnewdelivery;

    public static void sendObject(DeliveryDTO newDeliveryDTO) {
        gotnewdelivery = newDeliveryDTO;
    }

//    public static boolean placeOrder(String oId, String customerId, Double orderPayment, CartTM cartTM, List<OrdersDTO> ordersDTODtoList) {
//        Connection con = null;
//
//        try {
//            con = DBConnection.getInstance().getConnection();
//            con.setAutoCommit(false);
//
//            boolean isSaved = OrderModel.save(oId, customerId, orderPayment, LocalDate.now(), LocalTime.now(), cartTM);
//            if (isSaved) {
////                boolean isUpdated = ItemModel.updateQty(orderDtoList);
////                if (isUpdated) {
////                    boolean isplaced = OrderDetailModel.save(oId, orderDtoList);
////                    if (isplaced) {
////                        if (cartTM.getDelivery().equals("Yes")) {
////                            boolean isdeliverd = saveDeliver(gotnewdelivery);
////                            if (isdeliverd) {
////                                con.commit();
////                                return true;
////                            }
////                        } else {
////                            con.commit();
////                            return true;
////                        }
////                    }
////                }
//            }
//
//        } catch (Exception e) {
//            try {
//                con.rollback();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//
//
//        }
//        return false;
//    }

//    public static boolean saveDeliver(DeliveryDTO newDeliverDto) throws SQLException {
//
//        String Sql = "INSERT INTO delivery(deliveryId,deliveryLocation,orderId,empId) VALUES(?,?,?,?)";
//
//        return CrudUtil.execute(Sql,
//                newDeliverDto.getDeliverId(),
//                newDeliverDto.getLocation(),
//                newDeliverDto.getOrderId(),
//                newDeliverDto.getEmpId());

    }

