package lk.ijse.cafe_au_lait.bo.custom;

import lk.ijse.cafe_au_lait.bo.SuperBO;

import java.sql.SQLException;

public interface HomeBO<T,ID> extends SuperBO {
    int countCustomerId() throws SQLException;
}
