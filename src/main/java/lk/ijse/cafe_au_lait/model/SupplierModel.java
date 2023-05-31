package lk.ijse.cafe_au_lait.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cafe_au_lait.dto.Supplier;
import lk.ijse.cafe_au_lait.view.tdm.SupplierTM;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierModel {
    public static boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO supplier(SupplierId,SupplierName,SupplierContact" +
                ",SupplierAddress,SupplierEmail)VALUES (?,?,?,?,?)";

        return CrudUtil.execute(sql,
                supplier.getId(),
                supplier.getName(),
                supplier.getContact(),
                supplier.getAddress(),
                supplier.getEmail());
    }

    public static ObservableList<SupplierTM> getAll() throws SQLException {
        String sql = "SELECT * FROM Supplier";

        ObservableList<SupplierTM> supplierData = FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()) {
            supplierData.add(new SupplierTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return supplierData;

    }

    public static Supplier searchById(String text) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE SupplierId=?";

        ResultSet resultSet = CrudUtil.execute(sql, text);
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

    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET supplierName=?,supplierContact=?,supplierAddress=?" +
                ",supplierEmail=? WHERE supplierId=?";

        return CrudUtil.execute(sql,

                supplier.getName(),
                supplier.getContact(),
                supplier.getAddress(),
                supplier.getEmail(),
                supplier.getId());
    }

    public static boolean delete(String text) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supplierId=?";
        return CrudUtil.execute(sql, text);
    }

    public static ObservableList<String> loadSupplierIds() throws SQLException {
        String sql = "SELECT * FROM supplier";
        ResultSet resultSet = CrudUtil.execute(sql);
        ObservableList<String> supplierData = FXCollections.observableArrayList();
        while (resultSet.next()) {
            supplierData.add(
                    resultSet.getString(1)
            );
        }
        return supplierData;
    }
}
