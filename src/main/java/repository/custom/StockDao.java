package repository.custom;

import entity.StockEntity;
import entity.StockID;
import repository.CrudRepository;

public interface StockDao extends CrudRepository<StockEntity> {
    StockEntity search(StockID stockID);

}
