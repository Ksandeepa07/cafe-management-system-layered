package lk.ijse.cafe_au_lait.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cafe_au_lait.dto.SalaryDTO;
import lk.ijse.cafe_au_lait.view.tdm.SalaryTM;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryModel {


//    public static boolean save(SalaryDTO salaryDTO) throws SQLException {
//        String sql = "INSERT INTO Salary(salaryId,EmpId,salaryPaymentMethod,salaryPayment,salaryOt)" +
//                "VALUES(?,?,?,?,?)";
//
//        return CrudUtil.execute(sql,
//                salaryDTO.getSalaryId(),
//                salaryDTO.getEmpId(),
//                salaryDTO.getPaymentMethod(),
//                salaryDTO.getPayment(),
//                salaryDTO.getOverTime());
//    }

//    public static ObservableList<SalaryTM> getAll() throws SQLException {
//        String sql = "SELECT * FROM Salary";
//
//        ObservableList<SalaryTM> salaryData = FXCollections.observableArrayList();
//        ResultSet resultSet = CrudUtil.execute(sql);
//
//        while (resultSet.next()) {
//            salaryData.add(new SalaryTM(
//                    resultSet.getString(2),
//                    resultSet.getString(1),
//                    resultSet.getString(3),
//                    resultSet.getDouble(4),
//                    resultSet.getDouble(5)
//
//            ));
//
//        }
//        return salaryData;
//
//
//    }

//    public static boolean update(SalaryDTO salaryDTO) throws SQLException {
//        String sql = "UPDATE Salary SET empId=?,salaryPaymentMethod=?,salaryPayment=?,salaryOt=? WHERE" +
//                " salaryId=?";
//
//        return CrudUtil.execute(sql,
//
//                salaryDTO.getEmpId(),
//                salaryDTO.getPaymentMethod(),
//                salaryDTO.getPayment(),
//                salaryDTO.getOverTime(),
//                salaryDTO.getSalaryId());
//    }

//    public static boolean delete(String value) throws SQLException {
//        String sql = "DELETE FROM Salary WHERE salaryId=?";
//        return CrudUtil.execute(sql, value);
//    }
}
