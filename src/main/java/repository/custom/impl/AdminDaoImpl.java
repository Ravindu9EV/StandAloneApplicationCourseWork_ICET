package repository.custom.impl;

import entity.AdminEntity;
import entity.EmployeeEntity;
import org.hibernate.Session;
import repository.custom.AdminDao;
import util.HibernateUtil;

import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

public class AdminDaoImpl implements AdminDao {
    @Override
    public boolean save(AdminEntity entity) {
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(AdminEntity entity) {
        try{
            System.out.println("For Update"+entity);
            Session session=HibernateUtil.getSession();
            session.getTransaction().begin();

            session.createNativeQuery("UPDATE adminentity SET name='"+entity.getName()+"',location='"+entity.getLocation()+"',email='"+entity.getEmail()+"',password='"+entity.getPassword()+"',contact='"+entity.getContact()+"' WHERE id='"+entity.getId()+"'", EmployeeEntity.class).executeUpdate();
            session.getTransaction().commit();
            session.close();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<AdminEntity> findAll() {
        Session session=HibernateUtil.getSession();
        return session.createNativeQuery("Select * from adminentity", AdminEntity.class).stream().collect(Collectors.toList());

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
    public AdminEntity search(String id) {

            System.out.println("lplplp");
            Session session=HibernateUtil.getSession();
            session.setSubselectFetchingEnabled(true);
            AdminEntity a=session.find(AdminEntity.class,"A001");
            System.out.println(a);
            return a;

    }

    @Override
    public AdminEntity login(String email,String password) {
        Session session=HibernateUtil.getSession();

        AdminEntity entity= session.createNativeQuery("Select id,contact,email,location,name,password from adminentity  where email='"+email+"' and password='"+password+"'", AdminEntity.class).stream().findFirst().get();

        System.out.println(entity);



        System.out.println(email);
        return entity;
    }
}
