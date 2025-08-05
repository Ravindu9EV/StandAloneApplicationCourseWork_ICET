package service;

import service.custom.CustomerService;
import service.custom.impl.AdminServiceImpl;
import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.EmployeeServiceImpl;
import service.custom.impl.ItemServiceImpl;
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
            case CUSTOMER:return (T) new CustomerServiceImpl();
            case ITEM:return (T) new ItemServiceImpl();
            case EMPLOYEE:return (T) EmployeeServiceImpl.getInstance();
        }
        return null;
    }
}
