package lk.ijse.cafe_au_lait.model;

import lk.ijse.cafe_au_lait.db.DBConnection;
import lk.ijse.cafe_au_lait.dto.SupplyLoad;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class SupplyLoadModel {
    public static String getNextOrderId() throws SQLException {
        String sql = "SELECT supplierLoadId FROM supplierLoadDetail ORDER BY supplierLoadId DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

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

    public static boolean PlaceSupplyLoad(String supId, String supLoadId, String payment, List<SupplyLoad> data) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = SupplyLoadModel.saveSupplyLoad(supLoadId, supId, payment, LocalDate.now(), LocalTime.now(), data);
            if (isSaved) {
//                boolean isUpdated = ItemModel.updateSupplyQty(data);
//                if (isUpdated) {
//                    con.commit();
//                    return true;
//                }
            }
        } catch (Exception e) {
            System.out.println(e);

        }
        return false;
    }

    private static boolean saveSupplyLoad(String supLoadId, String supId, String payment, LocalDate now, LocalTime now1, List<SupplyLoad> data) throws SQLException {
        for (SupplyLoad supplyLoad : data) {
            if (!saveSupplyLoad(supLoadId, supId, payment, now, now1, supplyLoad)) {
                return false;
            }
        }
        return true;

    }

    private static boolean saveSupplyLoad(String supLoadId, String supId, String payment, LocalDate now, LocalTime now1, SupplyLoad supplyLoad) throws SQLException {
        String sql = "INSERT INTO supplierLoadDetail(supplierLoadId,itemId,supplierId," +
                "supplierQuantity,supplierLoadTime,supplierLoadDate,supplierLoadPricem)VALUES(" +
                "?,?,?,?,?,?,?)";

        return CrudUtil.execute(sql,
                supLoadId,
                supplyLoad.getItemId(),
                supId,
                supplyLoad.getQty(), now1,
                now, payment);

    }
}

