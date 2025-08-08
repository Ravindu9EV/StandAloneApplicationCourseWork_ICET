package repository.custom;

import entity.ItemEntity;
import repository.CrudRepository;

import java.util.List;

public interface ItemDao extends CrudRepository<ItemEntity> {
    List<String> getIDs();
    boolean updateStock(String id,Integer bought);
}
