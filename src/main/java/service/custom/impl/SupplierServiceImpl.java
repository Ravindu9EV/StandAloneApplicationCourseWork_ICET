package service.custom.impl;

import entity.EmployeeEntity;
import entity.SupplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.ID;
import model.Supplier;
import repository.DaoFactory;
import repository.custom.SupplierDao;
import repository.custom.impl.SupplierDaoImpl;
import service.custom.SupplierService;
import util.DaoType;
import org.modelmapper.*;

import java.util.Random;
import java.util.TreeSet;

import static java.lang.Math.PI;
import static java.lang.Math.abs;

public class SupplierServiceImpl implements SupplierService {
    private TreeSet<ID> ids;
    SupplierDao dao= DaoFactory.getDaoType(DaoType.SUPPLIER);
    private static SupplierServiceImpl instance;
    private SupplierServiceImpl(){
        getIDs();
    }
    public static SupplierServiceImpl getInstance(){
        return instance==null?instance=new SupplierServiceImpl():instance;
    }

    @Override
    public boolean add(Supplier supplier) {
        try{
            if (supplier.getId().isEmpty() ||supplier.getName().isEmpty() || supplier.getEmail().isEmpty() ||supplier.getContact().isEmpty() || supplier.getLocation().isEmpty()) {
                return false;
            }
            return dao.save(new ModelMapper().map(supplier, EmployeeEntity.class));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean edit(Supplier supplier) {
        try {
            if (supplier.getId().isEmpty() ||supplier.getName().isEmpty() || supplier.getEmail().isEmpty() ||supplier.getContact().isEmpty() || supplier.getLocation().isEmpty()) {
                return false;
            }
            return dao.update(new ModelMapper().map(supplier, EmployeeEntity.class));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        return id.isEmpty() ? false:dao.delete(id);
    }

    @Override
    public Supplier search(String id) {
        if(id.isEmpty()) {
            return null;
        }
        SupplierEntity entity=(SupplierEntity) dao.search(id);
        return entity!=null?new ModelMapper().map(entity, Supplier.class):null;
    }

    @Override
    public ObservableList<Supplier> getAll() {
        return null;
    }


    @Override
    public String getID() {
        ID id;
        Random r=new Random(10);
        do {
            id=new ID("SUP#" + abs(r.nextInt()) + 1);

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
        ObservableList<String> ids= FXCollections.observableArrayList();
        this.ids=new TreeSet<>();
        for(String id:dao.getIDs()){
            if(id.isEmpty()==false){
                ids.add(id);
                this.ids.add(new ID(id));
            }
        }

        return ids;
    }


}
