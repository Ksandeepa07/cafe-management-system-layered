package lk.ijse.cafe_au_lait.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cafe_au_lait.dto.ItemDTO;
import lk.ijse.cafe_au_lait.dto.Order;
import lk.ijse.cafe_au_lait.dto.SupplyLoad;
import lk.ijse.cafe_au_lait.view.tdm.ItemTM;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemModel {
//    public static boolean save(ItemDTO itemDTO) throws SQLException {
//        String sql = "INSERT INTO item(ItemId,ItemName,ItemQuantity" +
//                ",ItemUnitPrice,ItemCategory)VALUE(?,?,?,?,?)";
//
//        return CrudUtil.execute(sql,
//                itemDTO.getId(),
//                itemDTO.getName(),
//                itemDTO.getQuantity(),
//                itemDTO.getPrice(),
//                itemDTO.getCategory());
//    }

//    public static ObservableList<ItemTM> getAll() throws SQLException {
//        String sql = "SELECT * FROM item";
//
//        ObservableList<ItemTM> itemData = FXCollections.observableArrayList();
//        ResultSet resultSet = CrudUtil.execute(sql);
//        while (resultSet.next()) {
//            itemData.add(new ItemTM(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getInt(3),
//                    resultSet.getDouble(4),
//                    resultSet.getString(5)
//            ));
//        }
//        return itemData;
//
//    }

//    public static ItemDTO searchById(String text) {
//        String sql = "SELECT * FROM item WHERE itemId=?";
//        ResultSet resultSet = null;
//        try {
//            resultSet = CrudUtil.execute(sql, text);
//            if (resultSet.next()) {
//                return new ItemDTO(
//                        resultSet.getString(1),
//                        resultSet.getString(2),
//                        resultSet.getInt(3),
//                        resultSet.getDouble(4),
//                        resultSet.getString(5)
//                );
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        return null;
//    }

//    public static boolean update(ItemDTO itemDTO) throws SQLException {
//        String sql = "UPDATE item SET itemName=?,itemQuantity=?,itemUnitPrice=?,ItemCategory=? " +
//                "WHERE itemId=? ";
//        return CrudUtil.execute(sql,
//
//                itemDTO.getName(),
//                itemDTO.getQuantity(),
//                itemDTO.getPrice(),
//                itemDTO.getCategory(),
//                itemDTO.getId());
//    }

//    public static boolean delete(String text) throws SQLException {
//        String sql = "DELETE FROM item WHERE itemId=?";
//        return CrudUtil.execute(sql, text);
//    }

//    public static ObservableList<String> loadItemId() {
//        String sql = "SELECT * FROM item";
//        ObservableList<String> itemData = FXCollections.observableArrayList();
//        try {
//
//            ResultSet resultSet = CrudUtil.execute(sql);
//            while (resultSet.next()) {
//                itemData.add(
//                        resultSet.getString(1)
//                );
//            }
//            return itemData;
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public static boolean updateQty(List<Order> orderDtoList) {
//        for (Order orderDto : orderDtoList) {
//            if (!updateQty(orderDto)) {
//                return false;
//            }
//        }
//        return true;
//
//    }
//
//    public static boolean updateQty(Order orderDto) {
//        String sql = "UPDATE item SET itemQuantity=(itemQuantity-?) WHERE itemId=?";
//        try {
//            return CrudUtil.execute(sql,
//                    orderDto.getQty(),
//                    orderDto.getItemId());
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return false;
//    }
//
//    public static boolean updateSupplyQty(List<SupplyLoad> data) throws SQLException {
//        for (SupplyLoad supplyLoad : data) {
//            if (!updateSupplyQty(supplyLoad)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public static boolean updateSupplyQty(SupplyLoad supplyLoad) throws SQLException {
//        String sql = "UPDATE item SET itemQuantity=(itemQuantity+?) WHERE itemId=?";
//        return CrudUtil.execute(sql,
//                supplyLoad.getQty(),
//                supplyLoad.getItemId());
//    }
}

