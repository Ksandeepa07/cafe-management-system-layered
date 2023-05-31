package lk.ijse.cafe_au_lait.dao;

import lk.ijse.cafe_au_lait.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.cafe_au_lait.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.cafe_au_lait.dao.custom.impl.ItemDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){

    }
    public static DAOFactory getInstance(){
        if (daoFactory==null){
            return new DAOFactory();
        }else {
            return daoFactory;
        }
    }

    public enum DAOTypes{
        CUSTOMER,ITEM,EMPLOYEE
    }

    public <T extends SuperDAO>T getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return (T) new CustomerDAOImpl();

            case ITEM:
                return (T) new ItemDAOImpl();

            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();

            default:
                return null;
        }
    }
}
