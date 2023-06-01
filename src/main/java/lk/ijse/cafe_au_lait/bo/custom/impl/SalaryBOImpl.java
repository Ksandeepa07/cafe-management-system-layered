package lk.ijse.cafe_au_lait.bo.custom.impl;

import lk.ijse.cafe_au_lait.bo.custom.SalaryBO;
import lk.ijse.cafe_au_lait.dao.DAOFactory;
import lk.ijse.cafe_au_lait.dao.custom.SalaryDAO;
import lk.ijse.cafe_au_lait.dto.SalaryDTO;
import lk.ijse.cafe_au_lait.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBOImpl implements SalaryBO<SalaryDTO,String> {
    SalaryDAO salaryDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SALARY);
    ArrayList<SalaryDTO> salaryData=new ArrayList<>();
    @Override
    public boolean saveSalary(SalaryDTO salaryDTO) throws SQLException {
        return salaryDAO.save(new Salary(salaryDTO.getEmpId(),salaryDTO.getSalaryId(),salaryDTO.getPaymentMethod(),salaryDTO.getPayment(),salaryDTO.getOverTime()));
    }

    @Override
    public ArrayList<SalaryDTO> getAllSalary() throws SQLException {
        ArrayList<Salary> load=salaryDAO.getAll();
        for (Salary salary : load) {
            salaryData.add(new SalaryDTO(salary.getEmpId(),salary.getSalaryId(),salary.getSalaryPaymentMethod(),salary.getSalaryPayment(),salary.getSalaryOt()));
        }
        return salaryData;
    }

    @Override
    public boolean updateSalary(SalaryDTO salaryDTO) throws SQLException {
        return salaryDAO.update(new Salary(salaryDTO.getEmpId(),salaryDTO.getSalaryId(),salaryDTO.getPaymentMethod(),salaryDTO.getPayment(),salaryDTO.getOverTime()));
    }

    @Override
    public boolean deleteSalary(String id) throws SQLException {
        return salaryDAO.delete(id);
    }

    @Override
    public SalaryDTO searchSalaryById(String id) throws SQLException {
        Salary salary=salaryDAO.searchById(id);
        System.out.println(salary.getEmpId());
        System.out.println(salary.getSalaryId());
        return new SalaryDTO(salary.getEmpId(),salary.getSalaryId(),salary.getSalaryPaymentMethod(),salary.getSalaryPayment(),salary.getSalaryOt());
    }
}
