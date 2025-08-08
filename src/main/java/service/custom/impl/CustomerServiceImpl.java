package service.custom.impl;

import entity.CustomerEntity;
import entity.EmployeeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Customer;
import model.Employee;
import model.ID;
import model.Password;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.CustomerDao;
import service.custom.CustomerService;
import util.DaoType;

import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.TreeSet;

import static java.lang.Math.abs;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao dao=DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
    private static CustomerServiceImpl instance;
    TreeSet<ID> ids;
    private CustomerServiceImpl(){
        loadIDs();
    }
    public static CustomerServiceImpl getInstance(){
        return instance==null?instance=new CustomerServiceImpl():instance;
    }
    @Override
    public boolean add(Customer customer) {
        try{

            //password
            String p= Password.toHexString(Password.getSHA(customer.getPassword()));
            customer.setPassword(p);
            System.out.println(p);
            System.out.println(Password.toHexString(Password.getSHA(customer.getPassword())));
            String id=getID();
            System.out.println(id);
            CustomerEntity entity=new ModelMapper().map(customer,CustomerEntity.class);
            return dao.save(entity);
        }catch (Exception e){
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return false;
        }
    }

    @Override
    public boolean edit(Customer customer) {
        try {
            customer.setPassword(Password.toHexString(Password.getSHA(customer.getPassword())));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        try{
            CustomerEntity entity=dao.search(customer.getId());

            if(entity!=null){
                entity.setContact(customer.getContact());
                entity.setName(customer.getName());
                entity.setEmail(customer.getEmail());
                entity.setPassword(customer.getPassword());
                entity.setLocation(customer.getLocation());

                return dao.update(entity);
            }
            //new Alert(Alert.AlertType.ERROR,"Update Not Success!\nBecause Employee Not Exist..").show();
            return false;
        }catch (Exception e){
            //new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        return id.isEmpty()||id==null?false: dao.delete(id);
    }

    @Override
    public Customer search(String id) {
        CustomerEntity entity= dao.search(id);
        return entity!=null?new ModelMapper().map(entity, Customer.class):null;
    }

    @Override
    public ObservableList<Customer> getAll() {
        ObservableList<Customer> customers= FXCollections.observableArrayList();
        for (CustomerEntity entity:dao.findAll()){
            if(entity!=null){

                customers.add(new ModelMapper().map(entity, Customer.class));
            }
        }
        return customers;
    }



    @Override
    public String getID(){

        ID id;
        Random r=new Random(10);
        do {
            id=new ID("C#" + abs(r.nextInt()) + 1);

            System.out.println(id);
            //System.out.println("Contains: " + ids.contains((ID) ID.getInstance().setId("id")));
        }while (ids.contains(id));


        System.out.println(ids.add(id));
        System.out.println(ids);
        System.out.println("Contains: "+ids.contains((ID)new ID("ids")));

        return id.toString();
    }

    private void loadIDs(){
        TreeSet<ID> ids=new TreeSet<>();
        for(String id:dao.getIDs()){
            if(id!=null){
                ids.add(new ID(id));
            }
        }
        this.ids=ids;
    }
}
