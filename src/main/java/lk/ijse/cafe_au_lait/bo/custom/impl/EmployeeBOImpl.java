package lk.ijse.cafe_au_lait.bo.custom.impl;

import lk.ijse.cafe_au_lait.bo.custom.EmployeeBO;
import lk.ijse.cafe_au_lait.dao.DAOFactory;
import lk.ijse.cafe_au_lait.dao.custom.EmployeeDAO;
import lk.ijse.cafe_au_lait.dto.EmployeeDTO;
import lk.ijse.cafe_au_lait.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO<EmployeeDTO,String>{
    EmployeeDAO employeeDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public Boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException {
        return employeeDAO.save(new Employee(employeeDTO.getId(),employeeDTO.getName(),employeeDTO.getAddress(),employeeDTO.getDob(),employeeDTO.getNic(),employeeDTO.getJobTitle(),
                employeeDTO.getContact(),employeeDTO.getEmail()));
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException {
        ArrayList<EmployeeDTO> employeeData=new ArrayList<>();
        ArrayList<Employee> load=employeeDAO.getAll();
        for (Employee employee : load) {
            employeeData.add(new EmployeeDTO(employee.getId(),employee.getName(),employee.getAddress(),employee.getDob(),employee.getNic(),employee.getJobTitle(),employee.getContact(),employee.getEmail()));
        }
        return employeeData;
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException {
        return employeeDAO.update(new Employee(employeeDTO.getId(),employeeDTO.getName(),employeeDTO.getAddress(),employeeDTO.getDob(),employeeDTO.getNic(),employeeDTO.getJobTitle(),
                employeeDTO.getContact(),employeeDTO.getEmail()));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException {
        return employeeDAO.delete(id);
    }

    @Override
    public EmployeeDTO searchEmployeeById(String id) throws SQLException {
        Employee employee=employeeDAO.searchById(id);
        return new EmployeeDTO(employee.getId(),employee.getName(),employee.getAddress(),employee.getDob(),employee.getNic(),employee.getJobTitle(),
                employee.getContact(),employee.getEmail());
    }

    @Override
    public ArrayList<String> loadEmployeeIds() throws SQLException {
        return employeeDAO.loadIds();
    }
}
