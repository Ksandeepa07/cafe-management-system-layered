package lk.ijse.cafe_au_lait.bo.custom.impl;

import lk.ijse.cafe_au_lait.bo.custom.SupplierLoadBO;
import lk.ijse.cafe_au_lait.dao.DAOFactory;
import lk.ijse.cafe_au_lait.dao.custom.ItemDAO;
import lk.ijse.cafe_au_lait.dao.custom.SupplierDAO;
import lk.ijse.cafe_au_lait.dao.custom.SupplierLoadDAO;
import lk.ijse.cafe_au_lait.dao.custom.impl.SupplierLoadDAOImpl;
import lk.ijse.cafe_au_lait.db.DBConnection;
import lk.ijse.cafe_au_lait.dto.ItemDTO;
import lk.ijse.cafe_au_lait.dto.SupplierDTO;
import lk.ijse.cafe_au_lait.dto.SupplierLoadDetailDTO;
import lk.ijse.cafe_au_lait.dto.SupplyLoadDTO;
import lk.ijse.cafe_au_lait.entity.Item;
import lk.ijse.cafe_au_lait.entity.Supplier;
import lk.ijse.cafe_au_lait.entity.SupplierLoadDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class SupplierLoadBOImpl implements SupplierLoadBO {
    ItemDAO itemDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
    SupplierDAO supplierDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    SupplierLoadDAO supplierLoadDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIERLOAD);

    @Override
    public ItemDTO searchItemById(String id) throws SQLException {
        Item item=itemDAO.searchById(id);
        return new ItemDTO(item.getId(),item.getName(),item.getQuantity(),item.getPrice(),item.getCategory());
    }

    @Override
    public SupplierDTO searchSupplierById(String id) throws SQLException {
        Supplier supplier=supplierDAO.searchById(id);
        return new SupplierDTO(supplier.getId(),supplier.getName(),supplier.getContact(),supplier.getAddress(),supplier.getEmail());
    }

    @Override
    public ArrayList<String> loadSupplierIds() throws SQLException {
        return supplierDAO.loadIds();
    }

    @Override
    public ArrayList<String> loadItemId() {
        return itemDAO.loadIds();
    }

    @Override
    public String generatetNextSupplierLoadId() throws SQLException {
        return supplierDAO.generateNextId();
    }

    @Override
    public boolean PlaceSupplyLoadOrder(SupplyLoadDTO supplyLoadDTO) throws SQLException {

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            boolean isSaved=false;
            for (SupplierLoadDetailDTO supplierLoadDetailDTO : supplyLoadDTO.getSupplierLoadDetailDTOS()) {
                isSaved = supplierLoadDAO.save(new SupplierLoadDetail(supplyLoadDTO.getSupplierLoadId(), supplierLoadDetailDTO.getItemId(), supplyLoadDTO.getSupplieId(),
                        supplierLoadDetailDTO.getQty(), LocalTime.now(), LocalDate.now(), supplyLoadDTO.getPayment()));



                if (!isSaved) {
                    con.rollback();
                    con.setAutoCommit(true);
                    return false;
                }

               Item item = itemDAO.searchById(supplierLoadDetailDTO.getItemId());
                int quantity = item.getQuantity() + supplierLoadDetailDTO.getQty();
                System.out.println(quantity);

                boolean isUpdated = itemDAO.update(new Item(item.getId(),item.getName(),quantity,item.getPrice(),item.getCategory()));
                if (!isUpdated) {
                    con.rollback();
                    con.setAutoCommit(true);
                    return false;
                }


                }
            con.commit();
            return true;





        } catch (Exception e) {
            System.out.println(e);

        }
        return false;
    }
}
