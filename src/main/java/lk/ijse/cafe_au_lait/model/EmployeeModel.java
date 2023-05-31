package lk.ijse.cafe_au_lait.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cafe_au_lait.db.DBConnection;
import lk.ijse.cafe_au_lait.dto.EmployeeDTO;
import lk.ijse.cafe_au_lait.view.tdm.EmployeeTM;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {

    public static ObservableList<String> loadEmpIds() throws SQLException {
        String sql = "SELECT * FROM employee";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            ObservableList<String> employeeData = FXCollections.observableArrayList();
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                employeeData.add(resultSet.getString(1));
            }
            return employeeData;
        }
    }

//    public static boolean save(EmployeeDTO employeeDTO) throws SQLException {
//        String sql = "INSERT INTO Employee (EmpId,Name,Address,NIC,DOB,JobTitle,PhoneNumber,Email) VALUE (?,?,?,?,?,?,?,?)";
//
//        return CrudUtil.execute(sql,
//                employeeDTO.getId(),
//                employeeDTO.getName(),
//                employeeDTO.getAddress(),
//                employeeDTO.getNic(),
//                employeeDTO.getDob(),
//                employeeDTO.getJobTitle(),
//                employeeDTO.getContact(),
//                employeeDTO.getEmail()
//
//        );
//
//    }

//    public static ObservableList<EmployeeTM> getAll() throws SQLException {
//        ObservableList<EmployeeTM> data = FXCollections.observableArrayList();
//        String sql = "SELECT * FROM Employee";
//        ResultSet resultSet = null;
//
//        resultSet = CrudUtil.execute(sql);
//
//
//        while (resultSet.next()) {
//            data.add(new EmployeeTM(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(5),
//                    resultSet.getString(4),
//                    resultSet.getString(6),
//                    resultSet.getString(7),
//                    resultSet.getString(8)
//            ));
//        }
//
//
//        return data;
//
//    }

//    public static EmployeeDTO searchById(String text) throws SQLException {
//        String sql = "SELECT * FROM Employee WHERE EmpId=?";
//
//
//        ResultSet resultSet = null;
//        resultSet = CrudUtil.execute(sql, text);
//
//        if (resultSet.next()) {
//            return new EmployeeDTO(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(5),
//                    resultSet.getString(4),
//                    resultSet.getString(6),
//                    resultSet.getString(7),
//                    resultSet.getString(8)
//            );
//        }
//
//
//        return null;
//    }
//
//    public static boolean update(EmployeeDTO employeeDTO) throws SQLException {
//        String sql = "UPDATE Employee SET Name=?,Address=?,NIC=?,DOB=?,JobTitle=?,PhoneNumber=?,Email=? " +
//                "WHERE EmpId=? ";
//
//        return CrudUtil.execute(sql,
//                employeeDTO.getName(),
//                employeeDTO.getAddress(),
//                employeeDTO.getNic(),
//                employeeDTO.getDob(),
//                employeeDTO.getJobTitle(),
//                employeeDTO.getContact(),
//                employeeDTO.getEmail(),
//                employeeDTO.getId()
//
//        );
//
//
//    }

//    public static boolean delete(String text) throws SQLException {
//        String sql = "DELETE FROM Employee WHERE EmpId=?";
//
//        return CrudUtil.execute(sql,
//                text);
//
//    }
}
