package service.custom;

import model.Admin;
import service.SuperService;

public interface AdminService extends SuperService {
    boolean login(String email,String password);
    Admin findById(String id);
    boolean edit(Admin admin);
}
