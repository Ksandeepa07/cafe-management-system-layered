package lk.ijse.cafe_au_lait.dao.custom.impl;

import lk.ijse.cafe_au_lait.dao.custom.SalaryDAO;
import lk.ijse.cafe_au_lait.entity.Salary;
import lk.ijse.cafe_au_lait.controller.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public boolean save(Salary salary) throws SQLException {
        return CrudUtil.execute("INSERT INTO Salary(salaryId,EmpId,salaryPaymentMethod,salaryPayment,salaryOt)" +
                        "VALUES(?,?,?,?,?)",
                salary.getSalaryId(),
                salary.getEmpId(),
                salary.getSalaryPaymentMethod(),
                salary.getSalaryPayment(),
                salary.getSalaryOt());
    }

    @Override
    public ArrayList<Salary> getAll() throws SQLException {
        ArrayList<Salary> salaryData =new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute( "SELECT * FROM Salary");

        while (resultSet.next()) {
            salaryData.add(new Salary(
                    resultSet.getString(2),
                    resultSet.getString(1),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5)

            ));

        }
        return salaryData;
    }

    @Override
    public Salary searchById(String id) throws SQLException {
        ResultSet resultSet = null;
        try {
            resultSet = CrudUtil.execute("SELECT * FROM Salary WHERE salaryID=?", id);
            if (resultSet.next()) {
                return new Salary(
                        resultSet.getString(2),
                        resultSet.getString(1),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getDouble(5)
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    @Override
    public boolean update(Salary salary) throws SQLException {

        return CrudUtil.execute("UPDATE Salary SET empId=?,salaryPaymentMethod=?,salaryPayment=?,salaryOt=? WHERE" +
                        " salaryId=?",

                salary.getEmpId(),
                salary.getSalaryPaymentMethod(),
                salary.getSalaryPayment(),
                salary.getSalaryOt(),
                salary.getSalaryId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM Salary WHERE salaryId=?", id);
    }
}
