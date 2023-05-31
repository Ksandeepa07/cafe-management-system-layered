package lk.ijse.cafe_au_lait.bo;

import lk.ijse.cafe_au_lait.bo.custom.impl.CustomerBOImpl;
import lk.ijse.cafe_au_lait.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.cafe_au_lait.bo.custom.impl.ItemBOImpl;
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
        CUSTOMER,ITEM,EMPLOYEE
    }

    public <T extends SuperBO>T getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return (T) new CustomerBOImpl();

            case ITEM:
                return (T) new ItemBOImpl();

            case EMPLOYEE:
                return (T) new EmployeeBOImpl();

            default:
                return null;
        }

    }
}
