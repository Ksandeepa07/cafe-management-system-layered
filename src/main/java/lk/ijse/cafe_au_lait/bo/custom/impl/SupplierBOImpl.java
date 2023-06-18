package lk.ijse.cafe_au_lait.bo.custom.impl;

import lk.ijse.cafe_au_lait.bo.custom.SupplierBO;
import lk.ijse.cafe_au_lait.dao.DAOFactory;
import lk.ijse.cafe_au_lait.dao.custom.SupplierDAO;
import lk.ijse.cafe_au_lait.dto.SupplierDTO;
import lk.ijse.cafe_au_lait.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO<SupplierDTO,String> {
    SupplierDAO supplierDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);
     @Override
    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException {
        return supplierDAO.save(new Supplier(supplierDTO.getId(),supplierDTO.getName(),supplierDTO.getContact(),supplierDTO.getAddress(),supplierDTO.getEmail()));
    }

    @Override
    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException {
        ArrayList<SupplierDTO> supplierData=new ArrayList<>();
        ArrayList<Supplier> load=supplierDAO.getAll();
        for (Supplier supplier : load) {
            supplierData.add(new SupplierDTO(supplier.getId(),supplier.getName(),supplier.getContact(),supplier.getAddress(),supplier.getEmail()));
        }
        return supplierData;
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException {
        return supplierDAO.update(new Supplier(supplierDTO.getId(),supplierDTO.getName(),supplierDTO.getContact(),supplierDTO.getAddress(),supplierDTO.getEmail()));
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException {
        return supplierDAO.delete(id);
    }

    @Override
    public SupplierDTO searchSuppplierById(String id) throws SQLException {
        Supplier supplier=supplierDAO.searchById(id);
        return new SupplierDTO(supplier.getId(),supplier.getName(),supplier.getContact(),supplier.getAddress(),supplier.getEmail());
    }
}
