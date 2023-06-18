package lk.ijse.cafe_au_lait.bo.custom.impl;

import lk.ijse.cafe_au_lait.bo.BOFactory;
import lk.ijse.cafe_au_lait.bo.SuperBO;
import lk.ijse.cafe_au_lait.bo.custom.EmployeeBO;
import lk.ijse.cafe_au_lait.bo.custom.EventBO;
import lk.ijse.cafe_au_lait.dao.DAOFactory;
import lk.ijse.cafe_au_lait.dao.custom.EmployeeDAO;
import lk.ijse.cafe_au_lait.dao.custom.EventDAO;
import lk.ijse.cafe_au_lait.dto.EventDTO;
import lk.ijse.cafe_au_lait.entity.Event;

import java.sql.SQLException;
import java.util.ArrayList;

public class EventBOImpl implements EventBO<EventDTO,String>{
    EventDAO eventDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EVENT);
    EmployeeDAO employeeDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public boolean saveEvent(EventDTO eventDTO1) throws SQLException {
        return eventDAO.save(new Event(eventDTO1.getEmpId(),eventDTO1.getEventId(),eventDTO1.getEventName(),eventDTO1.getEventType(),eventDTO1.getEventDate(),eventDTO1.getEventTime()));
    }

    @Override
    public ArrayList<EventDTO> getAllEvent() throws SQLException {
        ArrayList<EventDTO> eventData=new ArrayList<>();
        ArrayList<Event> load=eventDAO.getAll();
        for (Event event : load) {
            eventData.add(new EventDTO(event.getEmpId(),event.getEventId(),event.getEventName(),event.getEventType(),event.getEventDate(),event.getEventTime()));
        }
        return eventData;
    }

    @Override
    public EventDTO searchEventById(String id) throws SQLException {
        Event event=eventDAO.searchById(id);
        return new EventDTO(event.getEmpId(),event.getEventId(),event.getEventName(),event.getEventType(),event.getEventDate(),event.getEventTime());
    }

    @Override
    public boolean deleteEvent(String id) throws SQLException {
        return eventDAO.delete(id);
    }

    @Override
    public boolean updateEvent(EventDTO eventDTO1) throws SQLException {
        return eventDAO.update(new Event(eventDTO1.getEmpId(),eventDTO1.getEventId(),eventDTO1.getEventName(),eventDTO1.getEventType(),eventDTO1.getEventDate(),eventDTO1.getEventTime()));

    }

    @Override
    public ArrayList<String> loadEmployeeIds() throws SQLException {
        return employeeDAO.loadIds();
    }
}
