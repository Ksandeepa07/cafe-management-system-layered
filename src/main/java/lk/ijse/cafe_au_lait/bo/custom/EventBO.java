package lk.ijse.cafe_au_lait.bo.custom;

import lk.ijse.cafe_au_lait.bo.SuperBO;
import lk.ijse.cafe_au_lait.dto.EventDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EventBO<T,ID> extends SuperBO {
    boolean saveEvent(EventDTO eventDTO1) throws SQLException;

    ArrayList<EventDTO> getAllEvent() throws SQLException;

    EventDTO searchEventById(String text) throws SQLException;

    boolean deleteEvent(String text) throws SQLException;

    boolean updateEvent(EventDTO eventDTO1) throws SQLException;

    ArrayList<String> loadEmployeeIds() throws SQLException;
}
