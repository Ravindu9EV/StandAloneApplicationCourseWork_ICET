package repository.custom.impl;

import entity.ItemEntity;
import repository.custom.ItemDao;

import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(ItemEntity entity) {
        return false;
    }

    @Override
    public boolean update(ItemEntity entity) {
        return false;
    }

    @Override
    public List<ItemEntity> findAll() {
        return List.of();
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public ItemEntity search(String id) {
        return null;
    }
}
