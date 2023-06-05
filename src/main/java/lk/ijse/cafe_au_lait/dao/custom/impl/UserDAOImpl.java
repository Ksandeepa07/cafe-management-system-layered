package lk.ijse.cafe_au_lait.dao.custom.impl;

import lk.ijse.cafe_au_lait.dao.custom.UserDAO;
import lk.ijse.cafe_au_lait.db.DBConnection;
import lk.ijse.cafe_au_lait.dto.UserDTO;
import lk.ijse.cafe_au_lait.entity.User;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User user) throws SQLException {

        return CrudUtil.execute("INSERT INTO User(Username,Password,Email,JobTitle)VALUES(?,?,?,?)",
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getJobTitle());
    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
        return null;
    }

    @Override
    public User searchById(String username) throws SQLException {
        ResultSet resultSet = null;

        resultSet = CrudUtil.execute("SELECT * FROM User WHERE username=?",
                username);
        if (resultSet.next()) {
            return new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }


        return null;
    }

    @Override
    public boolean update(User user) throws SQLException {
        String sql = "UPDATE User SET password=? WHERE Username=?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, user.getPassword());
            pstm.setString(2, user.getUsername());
            int rows = pstm.executeUpdate();
            if (rows > 0) {
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return false;
    }
}
