package service.custom.impl;

import entity.EmployeeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Employee;
import model.ID;
import repository.DaoFactory;
import repository.custom.EmployeeDao;
import service.custom.EmployeeService;
import util.DaoType;
import org.modelmapper.*;

import java.util.Random;
import java.util.TreeSet;

import static java.lang.Math.abs;

public class EmployeeServiceImpl implements EmployeeService {
    private static EmployeeServiceImpl instance;

    private TreeSet<ID> ids;
    EmployeeDao dao=DaoFactory.getDaoType(DaoType.EMPLOYEE);
    private EmployeeServiceImpl(){

        loadIDs();
    }
    @Override
    public boolean addEmployee(Employee employee) {
        try{
            String id=getID();
            System.out.println(id);
            EmployeeEntity entity=new ModelMapper().map(employee,EmployeeEntity.class);
            return dao.save(entity);
        }catch (Exception e){
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return false;
        }

    }

    @Override
    public boolean editEmployee(Employee employee) {
        try{
            EmployeeEntity entity=dao.search(employee.getId());

            if(entity!=null){
                entity.setContact(employee.getContact());
                entity.setName(employee.getName());
                entity.setEmail(employee.getEmail());
                entity.setPassword(employee.getPassword());
                entity.setLocation(employee.getLocation());

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
    public boolean deleteEmployee(String id) {
        return id.isEmpty()||id==null?false: dao.delete(id);
    }

    @Override
    public Employee search(String id) {

        EmployeeEntity entity= dao.search(id);
        return entity!=null?new ModelMapper().map(entity, Employee.class):null;
    }

    @Override
    public ObservableList<Employee> getAll() {
        ObservableList<Employee> employees= FXCollections.observableArrayList();
        for (EmployeeEntity entity:dao.findAll()){
            if(entity!=null){
                employees.add(new ModelMapper().map(entity, Employee.class));
            }
        }
        return employees;
    }

    @Override
    public boolean login(String email, String password) {
        return email.isEmpty()||password.isEmpty()?false:dao.login(email,password);
    }
    @Override
    public String getID(){

        ID id;
        Random r=new Random(10);
        do {
            id=new ID("EMP#" + abs(r.nextInt()) + 1);

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
    public static EmployeeServiceImpl getInstance(){
        return instance==null?instance=new EmployeeServiceImpl():instance;
    }
}
