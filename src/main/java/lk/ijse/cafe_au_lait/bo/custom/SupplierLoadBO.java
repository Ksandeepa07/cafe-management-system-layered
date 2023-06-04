package lk.ijse.cafe_au_lait.bo.custom;

import lk.ijse.cafe_au_lait.bo.SuperBO;
import lk.ijse.cafe_au_lait.dto.ItemDTO;
import lk.ijse.cafe_au_lait.dto.SupplierDTO;
import lk.ijse.cafe_au_lait.dto.SupplyLoadDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierLoadBO extends SuperBO {
    ItemDTO searchItemById(String id) throws SQLException;

    SupplierDTO searchSupplierById(String id) throws SQLException;

    ArrayList<String> loadSupplierIds() throws SQLException;

    ArrayList<String> loadItemId();

    String generatetNextSupplierLoadId() throws SQLException;

    boolean PlaceSupplyLoadOrder(SupplyLoadDTO supplyLoadDTO) throws SQLException;
}
