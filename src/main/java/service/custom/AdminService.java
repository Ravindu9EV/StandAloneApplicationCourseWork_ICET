package service.custom;

import service.SuperService;

public interface AdminService extends SuperService {
    boolean login(String email,String password);
}
