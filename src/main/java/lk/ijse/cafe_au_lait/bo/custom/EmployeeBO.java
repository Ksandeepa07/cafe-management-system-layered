package lk.ijse.cafe_au_lait.bo.custom;

import lk.ijse.cafe_au_lait.bo.SuperBO;
import lk.ijse.cafe_au_lait.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO<T,ID> extends SuperBO {
    Boolean saveEmployee(T t) throws SQLException;

    ArrayList<T> getAllEmployee() throws SQLException;

    boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException;

    boolean deleteEmployee(String text) throws SQLException;

    EmployeeDTO searchEmployeeById(String text) throws SQLException;

    ArrayList<String> loadEmployeeIds() throws SQLException;
}
