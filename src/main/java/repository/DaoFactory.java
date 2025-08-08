package repository;

import repository.custom.EmployeeDao;
import repository.custom.impl.*;
import util.DaoType;
import util.DaoType.*;
import java.lang.reflect.Type;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){

    }
    public static DaoFactory getInstance(){
        return instance==null? instance=new DaoFactory():instance;
    }

    public static <T extends SuperDao>T getDaoType(DaoType type){
        switch (type){
            case ADMIN:return (T) new AdminDaoImpl();
            case EMPLOYEE:return (T) new EmployeeDaoImpl();
            case CUSTOMER:return (T) new CustomerDaoImpl();
            case ITEM:return (T) new ItemDaoImpl();
            case STOCK:return (T) new StockDaoImpl();
        }
        return null;
    }
}
