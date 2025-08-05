package repository.custom.impl;

import entity.AdminEntity;
import org.hibernate.Session;
import repository.custom.AdminDao;
import util.HibernateUtil;

import java.sql.ResultSet;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    @Override
    public boolean save(AdminEntity entity) {
        return false;
    }

    @Override
    public boolean update(AdminEntity entity) {
        return false;
    }

    @Override
    public List<AdminEntity> findAll() {
        return List.of();
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public AdminEntity search(String id) {
        System.out.println("lplplp");
        Session session=HibernateUtil.getSession();
        session.setSubselectFetchingEnabled(true);
        AdminEntity a=(AdminEntity) session.byId("A001");
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
