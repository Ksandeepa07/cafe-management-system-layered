package lk.ijse.cafe_au_lait.model;

import lk.ijse.cafe_au_lait.db.DBConnection;
import lk.ijse.cafe_au_lait.dto.UserDTO;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
//    public static boolean save(UserDTO userDTO) throws SQLException {
////        String sql = "INSERT INTO user(Username,Password,Email,JobTitle)VALUES(?,?,?,?) ";
////        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
////            pstm.setString(1, user.getUsername());
////            pstm.setString(2, user.getPassword());
////            pstm.setString(3, user.getEmial());
////            pstm.setString(4, user.getJobTitle());
////            int rows = pstm.executeUpdate();
////
////            if (rows < 0) {
////                return true;
////            }
////        }
////        return false;
//
//        String sql = "INSERT INTO user(Username,Password,Email,JobTitle)VALUES(?,?,?,?)";
//
//        return CrudUtil.execute(sql,
//                userDTO.getUsername(),
//                userDTO.getPassword(),
//                userDTO.getEmial(),
//                userDTO.getJobTitle());
//
//
//    }

//    public static UserDTO SearchById(String username) throws SQLException {
//
//
//        String sql = "SELECT * FROM User WHERE username=?";
//        ResultSet resultSet = null;
//
//        resultSet = CrudUtil.execute(sql,
//                username);
//        if (resultSet.next()) {
//            return new UserDTO(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4)
//            );
//        }
//
//
//        return null;
//    }

//    public static boolean updatePassword(String usernamee, String password) throws SQLException {
//        String sql = "UPDATE User SET password=? WHERE Username=?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//            pstm.setString(1, password);
//            pstm.setString(2, usernamee);
//            int rows = pstm.executeUpdate();
//
//            if (rows > 0) {
//                return true;
//            }
//
//        }
//        return false;
//    }

}
