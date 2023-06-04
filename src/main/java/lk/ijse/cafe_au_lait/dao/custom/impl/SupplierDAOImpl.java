package lk.ijse.cafe_au_lait.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cafe_au_lait.dao.SuperDAO;
import lk.ijse.cafe_au_lait.dao.custom.SupplierDAO;
import lk.ijse.cafe_au_lait.dto.SupplierDTO;
import lk.ijse.cafe_au_lait.entity.Supplier;
import lk.ijse.cafe_au_lait.util.CrudUtil;
import lk.ijse.cafe_au_lait.view.tdm.SupplierTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean save(Supplier supplier) throws SQLException {

        return CrudUtil.execute("INSERT INTO supplier(SupplierId,SupplierName,SupplierContact" +
                ",SupplierAddress,SupplierEmail)VALUES (?,?,?,?,?)",
                supplier.getId(),
                supplier.getName(),
                supplier.getContact(),
                supplier.getAddress(),
                supplier.getEmail());
    }

    @Override
    public ArrayList<Supplier> getAll() throws SQLException {
        ArrayList<Supplier> supplierData = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM supplier");

        while (resultSet.next()) {
            supplierData.add(new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return supplierData;

    }

    @Override
    public Supplier searchById(String id) throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM supplier WHERE SupplierId=?", id);
        if (resultSet.next()) {
            return new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return null;
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException {

        return CrudUtil.execute( "UPDATE supplier SET supplierName=?,supplierContact=?,supplierAddress=?" +
                        ",supplierEmail=? WHERE supplierId=?",

                supplier.getName(),
                supplier.getContact(),
                supplier.getAddress(),
                supplier.getEmail(),
                supplier.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM supplier WHERE supplierId=?", id);
    }


    @Override
    public ArrayList<String> loadIds() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM supplier");
        ArrayList<String> supplierData = new ArrayList<>();
        while (resultSet.next()) {
            supplierData.add(
                    resultSet.getString(1)
            );
        }
        return supplierData;
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT supplierLoadId FROM SupplierLoadDetail ORDER BY supplierLoadId DESC LIMIT 1");

        if (resultSet.next()) {
            return SplitSupplierLoadId(resultSet.getString(1));
        }
        return SplitSupplierLoadId(null);
    }

    private static String SplitSupplierLoadId(String string) {
        if (string != null) {
            String[] strings = string.split("load-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit = String.format("%03d", id);
            return "load-" + digit;

        }
        return "load-001";
    }
}

