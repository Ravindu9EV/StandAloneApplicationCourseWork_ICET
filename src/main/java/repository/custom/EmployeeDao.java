package repository.custom;

import entity.EmployeeEntity;
import model.ID;
import repository.CrudRepository;

import java.util.List;

public interface EmployeeDao extends CrudRepository<EmployeeEntity> {
    boolean login(String email,String password);

    List<String> getIDs();
}
