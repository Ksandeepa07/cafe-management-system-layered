package lk.ijse.cafe_au_lait.bo.custom.impl;

import lk.ijse.cafe_au_lait.bo.custom.SignUpBO;
import lk.ijse.cafe_au_lait.dao.DAOFactory;
import lk.ijse.cafe_au_lait.dao.custom.UserDAO;
import lk.ijse.cafe_au_lait.dto.UserDTO;
import lk.ijse.cafe_au_lait.entity.User;

import java.sql.SQLException;

public class SignUpBOimpl implements SignUpBO {
    UserDAO userDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean saveUser(UserDTO userDTO) throws SQLException {
        return userDAO.save(new User(userDTO.getUsername(),userDTO.getPassword(),userDTO.getEmial(),userDTO.getJobTitle()));
    }
}
