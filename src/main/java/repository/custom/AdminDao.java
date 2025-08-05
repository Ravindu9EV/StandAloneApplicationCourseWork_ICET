package repository.custom;

import entity.AdminEntity;
import repository.CrudRepository;

public interface AdminDao extends CrudRepository<AdminEntity> {
    AdminEntity login(String email,String password);

}
