package lk.ijse.cafe_au_lait.bo.custom;

import lk.ijse.cafe_au_lait.bo.SuperBO;
import lk.ijse.cafe_au_lait.dao.SuperDAO;
import lk.ijse.cafe_au_lait.dto.UserDTO;

import java.sql.SQLException;

public interface ForgotPasswordBO extends SuperBO {

    UserDTO SearchUserById(String username) throws SQLException;

    boolean updateUserPassword(UserDTO userDTO) throws SQLException;
}
