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
        CUSTOMER,ITEM,EMPLOYEE,SUPPLIER,SALARY,EVENT,EVENTIMAGE,ORDERS,ORDER_DETAIL,DELIVERY,USER,SUPPLIERLOAD
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

            case EVENT:
            return (T) new EventDAOImpl();

            case EVENTIMAGE:
                return (T) new EventImageDAOImpl();

            case ORDERS:
                return (T) new OrdersDAOImpl();

            case ORDER_DETAIL:
                return (T) new OrderDetailDAOImpl();

            case DELIVERY:
                return (T) new DeliveryDAOImpl();

            case USER:
                return (T) new UserDAOImpl();

            case SUPPLIERLOAD:
                return (T) new SupplierLoadDAOImpl();

            default:
                return null;
        }
    }
}
