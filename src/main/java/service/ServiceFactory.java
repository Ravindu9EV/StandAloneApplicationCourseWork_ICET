package service;

import service.custom.CustomerService;
import service.custom.SupplierService;
import service.custom.impl.*;
import util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory(){}

    public static ServiceFactory getInstance() {
        return instance==null?instance=new ServiceFactory():instance;
    }
    public <T extends SuperService>T getService(ServiceType type){
        switch (type){
            case ADMIN:return (T) new AdminServiceImpl();
            case CUSTOMER:return (T) CustomerServiceImpl.getInstance();
            case ITEM:return (T) ItemServiceImpl.getInstance();
            case EMPLOYEE:return (T) EmployeeServiceImpl.getInstance();
            case SUPPLIER:return (T) SupplierServiceImpl.getInstance();
        }
        return null;
    }
}
