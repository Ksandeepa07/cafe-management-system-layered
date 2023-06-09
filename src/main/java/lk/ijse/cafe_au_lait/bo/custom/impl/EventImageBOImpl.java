package lk.ijse.cafe_au_lait.bo.custom.impl;

import lk.ijse.cafe_au_lait.bo.custom.EventImageBO;
import lk.ijse.cafe_au_lait.dao.DAOFactory;
import lk.ijse.cafe_au_lait.dao.custom.EventDAO;
import lk.ijse.cafe_au_lait.dao.custom.EventImageDAO;
import lk.ijse.cafe_au_lait.dto.EventImageDTO;
import lk.ijse.cafe_au_lait.entity.EventImage;

import java.sql.SQLException;
import java.util.ArrayList;

public class EventImageBOImpl implements EventImageBO<EventImageDTO,String> {
    EventImageDAO eventImageDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EVENTIMAGE);
    EventDAO eventDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EVENT);
    @Override
    public boolean saveImage(EventImageDTO eventImageDTO) throws SQLException {
        return eventImageDAO.save(new EventImage(eventImageDTO.getEventId(),eventImageDTO.getEventImage()));
    }

    @Override
    public ArrayList<String> loadEventIds() throws SQLException {
        return eventDAO.loadIds();
    }
}
