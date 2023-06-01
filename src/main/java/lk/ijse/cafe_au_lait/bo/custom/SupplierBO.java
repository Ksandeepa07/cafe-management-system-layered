package lk.ijse.cafe_au_lait.bo.custom;

import lk.ijse.cafe_au_lait.bo.SuperBO;
import lk.ijse.cafe_au_lait.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO <T,ID>extends SuperBO {
    boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException;

    ArrayList<SupplierDTO> getAllSuppliers() throws SQLException;

    boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException;

    boolean deleteSupplier(String text) throws SQLException;

    SupplierDTO searchSuppplierById(String text) throws SQLException;
}
