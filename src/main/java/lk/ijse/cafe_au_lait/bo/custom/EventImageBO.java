package lk.ijse.cafe_au_lait.bo.custom;

import lk.ijse.cafe_au_lait.bo.SuperBO;
import lk.ijse.cafe_au_lait.dto.EventImageDTO;

import java.sql.SQLException;

public interface EventImageBO<T,ID> extends SuperBO {
    boolean saveImage(EventImageDTO eventImageDTO) throws SQLException;
}
