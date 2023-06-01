package lk.ijse.cafe_au_lait.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cafe_au_lait.dao.custom.ItemDAO;
import lk.ijse.cafe_au_lait.dto.ItemDTO;
import lk.ijse.cafe_au_lait.entity.Item;
import lk.ijse.cafe_au_lait.util.CrudUtil;
import lk.ijse.cafe_au_lait.view.tdm.ItemTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item item) throws SQLException {
        return CrudUtil.execute("INSERT INTO Item(ItemId,ItemName,ItemQuantity" +
                        ",ItemUnitPrice,ItemCategory)VALUE(?,?,?,?,?)",
                item.getId(),
                item.getName(),
                item.getQuantity(),
                item.getPrice(),
                item.getCategory());
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException {
        ArrayList<Item> itemData =new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Item");
        while (resultSet.next()) {
            itemData.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
            ));
        }
        return itemData;
    }

    @Override
    public Item searchById(String id) {
        String sql = "SELECT * FROM Item WHERE ItemId=?";
        ResultSet resultSet = null;
        try {
            resultSet = CrudUtil.execute(sql, id);
            if (resultSet.next()) {
                return new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean update(Item item) throws SQLException {
        return CrudUtil.execute( "UPDATE Item SET itemName=?,itemQuantity=?,itemUnitPrice=?,ItemCategory=? " +
                        "WHERE itemId=? ",

                item.getName(),
                item.getQuantity(),
                item.getPrice(),
                item.getCategory(),
                item.getId());
    }



    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM Item WHERE itemId=?", id);
    }


}
