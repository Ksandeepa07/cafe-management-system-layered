package lk.ijse.cafe_au_lait.dao;

import lk.ijse.cafe_au_lait.dao.custom.impl.*;

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
        CUSTOMER,ITEM,EMPLOYEE,SUPPLIER,SALARY
    }

    public <T extends SuperDAO>T getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return (T) new CustomerDAOImpl();

            case ITEM:
                return (T) new ItemDAOImpl();

            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();

            case SUPPLIER:
                return (T) new SupplierDAOImpl();

            case SALARY:
            return (T) new SalaryDAOImpl();

            default:
                return null;
        }
    }
}
