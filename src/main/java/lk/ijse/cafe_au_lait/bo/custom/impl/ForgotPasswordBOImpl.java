package lk.ijse.cafe_au_lait.bo.custom.impl;

import lk.ijse.cafe_au_lait.bo.custom.ForgotPasswordBO;
import lk.ijse.cafe_au_lait.dao.DAOFactory;
import lk.ijse.cafe_au_lait.dao.custom.UserDAO;
import lk.ijse.cafe_au_lait.dto.UserDTO;
import lk.ijse.cafe_au_lait.entity.User;

import java.sql.SQLException;

public class ForgotPasswordBOImpl implements ForgotPasswordBO {
    UserDAO userDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public UserDTO SearchUserById(String username) throws SQLException {
        User user=userDAO.searchById(username);
        return new UserDTO(user.getUsername(),user.getPassword(),user.getEmail(),user.getJobTitle());

    }

    @Override
    public boolean updateUserPassword(UserDTO userDTO) throws SQLException {
        return userDAO.update(new User(userDTO.getUsername(),userDTO.getPassword(),userDTO.getEmial(),userDTO.getJobTitle()));
    }
}
