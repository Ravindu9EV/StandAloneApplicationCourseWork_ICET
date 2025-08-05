package service.custom.impl;

import entity.AdminEntity;
import repository.DaoFactory;
import repository.custom.AdminDao;
import service.ServiceFactory;
import service.custom.AdminService;
import util.DaoType;

public class AdminServiceImpl implements AdminService {
    private final AdminDao dao=DaoFactory.getInstance().getDaoType(DaoType.ADMIN);
    @Override
    public boolean login(String email, String password) {
        System.out.println("Hiii");
        System.out.println(email+""+password);
        AdminEntity entity=dao.login(email,password);
        System.out.println(entity);
        return email.isEmpty()|| password.isEmpty()?false:entity!=null;
    }
}
