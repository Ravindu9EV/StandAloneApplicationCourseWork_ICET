package repository.custom.impl;

import entity.AdminEntity;
import entity.EmployeeEntity;
import model.ID;
import org.hibernate.Session;
import repository.custom.EmployeeDao;
import util.HibernateUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean login(String email, String password) {
        Session session=HibernateUtil.getSession();

        EmployeeEntity entity= session.createNativeQuery("Select id,contact,email,location,name,password from employeeentity  where email='"+email+"' and password='"+password+"'", EmployeeEntity.class).stream().findFirst().get();

        System.out.println(entity);



        System.out.println(email);
        return entity!=null;
    }

    @Override
    public List<String> getIDs() {

        Session session= HibernateUtil.getSession();
       List<String> ids=session.createNativeQuery("SELECT id FROM employeeentity",String.class).getResultList();
        session.close();
       return ids;


    }

    @Override
    public boolean save(EmployeeEntity entity) {
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(EmployeeEntity entity) {
       try{
           System.out.println("For Update"+entity);
           Session session=HibernateUtil.getSession();
           session.getTransaction().begin();

           session.createNativeQuery("UPDATE employeeentity SET name='"+entity.getName()+"',location='"+entity.getLocation()+"',email='"+entity.getEmail()+"',password='"+entity.getPassword()+"',contact='"+entity.getContact()+"' WHERE id='"+entity.getId()+"'",EmployeeEntity.class).executeUpdate();
            session.getTransaction().commit();
           session.close();
           return true;
       }catch (Exception e){
           System.out.println(e.getMessage());
       }

        return false;
    }

    @Override
    public List<EmployeeEntity> findAll() {

        Session session=HibernateUtil.getSession();
        return session.createNativeQuery("Select * from employeeentity", EmployeeEntity.class).stream().collect(Collectors.toList());

    }

    @Override
    public boolean delete(String id) {
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        session.remove(search(id));
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public EmployeeEntity search(String id) {
        Session session=HibernateUtil.getSession();
        EmployeeEntity entity=session.find(EmployeeEntity.class,id);
        session.close();
        return entity;
    }
}
