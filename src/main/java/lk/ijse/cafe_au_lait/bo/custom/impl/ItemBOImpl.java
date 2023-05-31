package lk.ijse.cafe_au_lait.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.cafe_au_lait.bo.custom.ItemBO;
import lk.ijse.cafe_au_lait.dao.DAOFactory;
import lk.ijse.cafe_au_lait.dao.custom.ItemDAO;
import lk.ijse.cafe_au_lait.dao.custom.impl.ItemDAOImpl;
import lk.ijse.cafe_au_lait.dto.ItemDTO;
import lk.ijse.cafe_au_lait.entity.Item;
import lk.ijse.cafe_au_lait.util.CrudUtil;
import lk.ijse.cafe_au_lait.view.tdm.ItemTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO<ItemDTO,String> {
    ItemDAO itemDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
    ArrayList<ItemDTO> itemData=new ArrayList<>();

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException {
        return itemDAO.save(new Item(itemDTO.getId(),itemDTO.getName(),itemDTO.getQuantity(),itemDTO.getPrice(),itemDTO.getCategory()));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException {
        return itemDAO.delete(id);
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException {
        ArrayList<Item> load=itemDAO.getAll();
        for (Item item : load) {
            itemData.add(new ItemDTO(item.getId(),item.getName(),item.getQuantity(),item.getPrice(),item.getCategory()));

        }
        return itemData;

    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException {
        return itemDAO.update(new Item(itemDTO.getId(),itemDTO.getName(),itemDTO.getQuantity(),itemDTO.getPrice(),itemDTO.getCategory()));
    }

    @Override
    public ItemDTO searchItemById(String id) {
        Item item=itemDAO.searchById(id);
        return new ItemDTO(item.getId(),item.getName(),item.getQuantity(),item.getPrice(),item.getCategory());
    }

}
