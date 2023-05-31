package lk.ijse.cafe_au_lait.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cafe_au_lait.dto.Salary;
import lk.ijse.cafe_au_lait.view.tdm.SalaryTM;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryModel {


    public static boolean save(Salary salary) throws SQLException {
        String sql = "INSERT INTO salary(salaryId,EmpId,salaryPaymentMethod,salaryPayment,salaryOt)" +
                "VALUES(?,?,?,?,?)";

        return CrudUtil.execute(sql,
                salary.getSalaryId(),
                salary.getEmpId(),
                salary.getPaymentMethod(),
                salary.getPayment(),
                salary.getOverTime());
    }

    public static ObservableList<SalaryTM> getAll() throws SQLException {
        String sql = "SELECT * FROM salary";

        ObservableList<SalaryTM> salaryData = FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()) {
            salaryData.add(new SalaryTM(
                    resultSet.getString(2),
                    resultSet.getString(1),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5)

            ));

        }
        return salaryData;


    }

    public static boolean update(Salary salary) throws SQLException {
        String sql = "UPDATE salary SET empId=?,salaryPaymentMethod=?,salaryPayment=?,salaryOt=? WHERE" +
                " salaryId=?";

        return CrudUtil.execute(sql,

                salary.getEmpId(),
                salary.getPaymentMethod(),
                salary.getPayment(),
                salary.getOverTime(),
                salary.getSalaryId());
    }

    public static boolean delete(String value) throws SQLException {
        String sql = "DELETE FROM salary WHERE salaryId=?";
        return CrudUtil.execute(sql, value);
    }
}
