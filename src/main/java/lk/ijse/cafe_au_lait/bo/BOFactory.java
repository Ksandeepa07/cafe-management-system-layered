package lk.ijse.cafe_au_lait.bo;

import lk.ijse.cafe_au_lait.bo.custom.impl.*;
import lk.ijse.cafe_au_lait.dao.custom.impl.ItemDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }

    public static BOFactory getInstance(){
        if(boFactory==null){
            return new BOFactory();
        }else{
            return boFactory;
        }
    }

    public enum BOTypes{
        CUSTOMER,ITEM,EMPLOYEE,SUPPLIER,SALARY,EVENT,EVENTIMAGE,HOME,PLACE_ORDER,SUPPLIER_lOAD
    }

    public <T extends SuperBO>T getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return (T) new CustomerBOImpl();

            case ITEM:
                return (T) new ItemBOImpl();

            case EMPLOYEE:
                return (T) new EmployeeBOImpl();

            case SUPPLIER:
                return (T) new SupplierBOImpl();

            case SALARY:
                return (T) new SalaryBOImpl();

            case EVENT:
                return (T) new EventBOImpl();

            case EVENTIMAGE:
                return (T) new EventImageBOImpl();

            case HOME:
                return (T) new HomeBOImpl();

            case PLACE_ORDER:
            return (T) new PlaceOrderBOImpl();

            case SUPPLIER_lOAD:
                return (T) new SupplierLoadBOImpl();

            default:
                return null;
        }

    }
}
