package repository.custom;

import repository.CrudRepository;

import java.util.List;

public interface SupplierDao extends CrudRepository {
    List<String> getIDs();
}
