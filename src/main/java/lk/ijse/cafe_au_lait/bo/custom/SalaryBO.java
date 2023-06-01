package lk.ijse.cafe_au_lait.bo.custom;

import lk.ijse.cafe_au_lait.bo.SuperBO;
import lk.ijse.cafe_au_lait.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBO <T,ID> extends SuperBO {
    boolean saveSalary(SalaryDTO salaryDTO) throws SQLException;

    ArrayList<SalaryDTO> getAllSalary() throws SQLException;

    boolean updateSalary(SalaryDTO salaryDTO) throws SQLException;

    boolean deleteSalary(String text) throws SQLException;

    SalaryDTO searchSalaryById(String text) throws SQLException;
}
