package lk.ijse.cafe_au_lait.dao.custom.impl;

import lk.ijse.cafe_au_lait.dao.custom.SupplierLoadDAO;
import lk.ijse.cafe_au_lait.entity.SupplierLoadDetail;
import lk.ijse.cafe_au_lait.controller.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierLoadDAOImpl implements SupplierLoadDAO {
    @Override
    public boolean save(SupplierLoadDetail supplierLoadDetail) throws SQLException {
        return CrudUtil.execute("INSERT INTO SupplierLoadDetail(supplierLoadId,itemId,supplierId," +
                        "supplierQuantity,supplierLoadTime,supplierLoadDate,supplierLoadPricem)VALUES(" +
                        "?,?,?,?,?,?,?)",
                supplierLoadDetail.getSupplierLoadId(),
                supplierLoadDetail.getItemId(),
                supplierLoadDetail.getSupplierId(),
                supplierLoadDetail.getSupplierQty(), supplierLoadDetail.getSupplierLoadTime(),
                supplierLoadDetail.getSupplierLoadDate(),supplierLoadDetail.getSupplierLoadPrice());

    }

    @Override
    public ArrayList<SupplierLoadDetail> getAll() throws SQLException {
        return null;
    }

    @Override
    public SupplierLoadDetail searchById(String s) throws SQLException {
        return null;
    }

    @Override
    public boolean update(SupplierLoadDetail supplierLoadDetail) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return false;
    }
//    @Override
//    public boolean save(SupplierLoadDetail supplyLoadDTO) throws SQLException {
////        return CrudUtil.execute("INSERT INTO supplierLoadDetail(supplierLoadId,itemId,supplierId," +
////                        "supplierQuantity,supplierLoadTime,supplierLoadDate,supplierLoadPricem)VALUES(" +
////                        "?,?,?,?,?,?,?)",
////                supplyLoadDTO.getSupplierLoadId(),
////                supplyLoadDTO.(),
////                supplyLoadDTO.getSupplieId(),
////                supplyLoadDTO., now1,
////                now, payment);
//        return true;
////
//    }
//
//    @Override
//    public ArrayList<SupplyLoadDTO> getAll() throws SQLException {
//        return null;
//    }
//
//    @Override
//    public SupplyLoadDTO searchById(String s) throws SQLException {
//        return null;
//    }
//
//    @Override
//    public boolean update(SupplyLoadDTO supplyLoadDTO) throws SQLException {
//        return false;
//    }
//
//    @Override
//    public boolean delete(String s) throws SQLException {
//        return false;
//    }
}
