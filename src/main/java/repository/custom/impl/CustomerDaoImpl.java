package repository.custom.impl;

import entity.CustomerEntity;
import entity.EmployeeEntity;
import model.Customer;
import org.hibernate.Session;
import repository.custom.CustomerDao;
import util.HibernateUtil;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(CustomerEntity entity) {
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(CustomerEntity entity) {
        try{
            System.out.println("For Update"+entity);
            Session session=HibernateUtil.getSession();
            session.getTransaction().begin();

            session.createNativeQuery("UPDATE customerentity SET name='"+entity.getName()+"',location='"+entity.getLocation()+"',email='"+entity.getEmail()+"',password='"+entity.getPassword()+"',contact='"+entity.getContact()+"' WHERE id='"+entity.getId()+"'", EmployeeEntity.class).executeUpdate();
            session.getTransaction().commit();
            session.close();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<CustomerEntity> findAll() {
        Session session=HibernateUtil.getSession();
        return session.createNativeQuery("Select * from employeeentity", CustomerEntity.class).stream().collect(Collectors.toList());

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
    public CustomerEntity search(String id) {
        Session session=HibernateUtil.getSession();
        CustomerEntity entity=session.find(CustomerEntity.class,id);
        session.close();
        return entity;
    }

    @Override
    public List<String> getIDs() {
        Session session= HibernateUtil.getSession();
        List<String> ids=session.createNativeQuery("SELECT id FROM customerentity",String.class).getResultList();
        session.close();
        return ids;
    }
}
