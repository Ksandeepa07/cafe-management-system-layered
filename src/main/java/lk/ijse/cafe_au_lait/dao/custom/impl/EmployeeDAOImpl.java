package lk.ijse.cafe_au_lait.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cafe_au_lait.dao.custom.EmployeeDAO;
import lk.ijse.cafe_au_lait.dto.EmployeeDTO;
import lk.ijse.cafe_au_lait.entity.Employee;
import lk.ijse.cafe_au_lait.util.CrudUtil;
import lk.ijse.cafe_au_lait.view.tdm.EmployeeTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(Employee employee) throws SQLException {

        return CrudUtil.execute("INSERT INTO Employee (EmpId,Name,Address,NIC,DOB,JobTitle,PhoneNumber,Email) VALUE (?,?,?,?,?,?,?,?)",
                employee.getId(),
                employee.getName(),
                employee.getAddress(),
                employee.getNic(),
                employee.getDob(),
                employee.getJobTitle(),
                employee.getContact(),
                employee.getEmail()

        );
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException {
        ArrayList<Employee> data = new ArrayList<>();
        ResultSet resultSet = null;

        resultSet = CrudUtil.execute("SELECT * FROM Employee");

        while (resultSet.next()) {
            data.add(new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(5),
                    resultSet.getString(4),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            ));
        }


        return data;
    }

    @Override
    public Employee searchById(String id) throws SQLException {
        ResultSet resultSet = null;
        resultSet = CrudUtil.execute("SELECT * FROM Employee WHERE EmpId=?", id);

        if (resultSet.next()) {
            return new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(5),
                    resultSet.getString(4),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );
        }


        return null;
    }

    @Override
    public boolean update(Employee employee) throws SQLException {

        return CrudUtil.execute("UPDATE Employee SET Name=?,Address=?,NIC=?,DOB=?,JobTitle=?,PhoneNumber=?,Email=? " +
                        "WHERE EmpId=? ",
                employee.getName(),
                employee.getAddress(),
                employee.getNic(),
                employee.getDob(),
                employee.getJobTitle(),
                employee.getContact(),
                employee.getEmail(),
                employee.getId()

        );    }

    @Override
    public boolean delete(String id) throws SQLException {

        return CrudUtil.execute("DELETE FROM Employee WHERE EmpId=?",
                id);
    }

    @Override
    public ArrayList<String> loadIds() {
        return null;
    }

    @Override
    public int countId() throws SQLException {
        return 0;
    }
}
