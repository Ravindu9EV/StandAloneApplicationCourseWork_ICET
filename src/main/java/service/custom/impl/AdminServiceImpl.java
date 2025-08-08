package service.custom.impl;

import entity.AdminEntity;
import model.Admin;
import model.Password;
import repository.DaoFactory;
import repository.custom.AdminDao;
import service.ServiceFactory;
import service.custom.AdminService;
import util.DaoType;
import org.modelmapper.*;

import java.security.NoSuchAlgorithmException;

public class AdminServiceImpl implements AdminService {
    private final AdminDao dao=DaoFactory.getInstance().getDaoType(DaoType.ADMIN);
    @Override
    public boolean login(String email, String password) {
        try {
            password=Password.toHexString(Password.getSHA(password));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hiii");
        System.out.println(email+""+password);
        AdminEntity entity=dao.login(email,password);
        System.out.println(entity);
        return email.isEmpty()|| password.isEmpty()?false:entity!=null;
    }

    @Override
    public Admin findById(String id) {
        AdminEntity entity=dao.search(id);
        System.out.println("ent"+entity);
        return entity!=null?new ModelMapper().map(entity,Admin.class):null;
    }

    @Override
    public boolean edit(Admin admin) {
        try {
            admin.setPassword(Password.toHexString(Password.getSHA(admin.getPassword())));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        AdminEntity entity=new ModelMapper().map(admin,AdminEntity.class);
        return admin!=null?dao.update(entity):false;
    }
}
