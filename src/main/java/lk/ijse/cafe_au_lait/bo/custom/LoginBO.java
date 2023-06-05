package lk.ijse.cafe_au_lait.bo.custom;

import lk.ijse.cafe_au_lait.bo.SuperBO;
import lk.ijse.cafe_au_lait.dto.UserDTO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    UserDTO SearchUserById(String username) throws SQLException;
}
