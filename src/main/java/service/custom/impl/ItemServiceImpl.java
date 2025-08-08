package service.custom.impl;

import entity.ItemEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ID;
import model.Item;
import model.Supplier;
import repository.DaoFactory;
import repository.custom.ItemDao;
import service.custom.ItemService;
import util.DaoType;
import org.modelmapper.*;

import java.util.Random;
import java.util.TreeSet;

import static java.lang.Math.abs;

public class ItemServiceImpl implements ItemService {
    TreeSet<ID> ids;
    private static ItemServiceImpl instance;
    private ItemDao dao;
    private ItemServiceImpl(){
        dao= DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        getIDs();
    }
    public static ItemServiceImpl getInstance(){
        return instance==null?instance=new ItemServiceImpl():instance;
    }
    @Override
    public boolean add(Item item) {

            if(item.getId().isEmpty()||item.getName().isEmpty()||item.getQuantity()<=0||item.getBrand().isEmpty()||item.getSupplierID().isEmpty()||item.getPrice()<=0||item.getSizeID().isEmpty()){
                return false;
            }

            return dao.save(new ModelMapper().map(item, ItemEntity.class));

    }

    @Override
    public boolean edit(Item item) {
        if(item.getId().isEmpty()||item.getName().isEmpty()||item.getQuantity()<=0||item.getBrand().isEmpty()||item.getSupplierID().isEmpty()||item.getPrice()<=0||item.getSizeID().isEmpty()){
            return false;
        }
        return dao.update(new ModelMapper().map(item, ItemEntity.class));
    }

    @Override
    public boolean delete(String id) {
        return id.isEmpty()?false:dao.delete(id);
    }

    @Override
    public Item search(String id) {
        ItemEntity entity;
        if(id.isEmpty()) return null;
        entity=dao.search(id);
        return entity!=null?new ModelMapper().map(entity,Item.class):null;
    }

    @Override
    public ObservableList<Item> getAll() {
        ObservableList<Item> items= FXCollections.observableArrayList();
        for(ItemEntity item: dao.findAll()){
            if(item!=null){
                items.add(new ModelMapper().map(item, Item.class));
            }
        }

        return items;
    }

    @Override
    public String getID() {
        ID id;
        Random r=new Random(10);
        do {
            id=new ID("CL#" + abs(r.nextInt()) + 1);

            System.out.println(id);
            //System.out.println("Contains: " + ids.contains((ID) ID.getInstance().setId("id")));
        }while (ids.contains(id));


        System.out.println(ids.add(id));
        System.out.println(ids);
        System.out.println("Contains: "+ids.contains((ID)new ID("ids")));

        return id.toString();
    }

    @Override
    public ObservableList<String> getIDs() {
        ObservableList<String> ids=FXCollections.observableArrayList();
        this.ids=new TreeSet<>();
        for(String id:dao.getIDs()){
            ids.add(id);
            this.ids.add(new ID(id));
        }
        return ids;
    }
}
