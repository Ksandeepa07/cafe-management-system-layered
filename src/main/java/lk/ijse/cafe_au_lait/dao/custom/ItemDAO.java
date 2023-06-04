package lk.ijse.cafe_au_lait.dao.custom;

import lk.ijse.cafe_au_lait.dao.CrudDAO;
import lk.ijse.cafe_au_lait.entity.Item;

import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item,String> {
    ArrayList<String> loadIds();
}
