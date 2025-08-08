package repository.custom.impl;

import org.hibernate.Session;
import repository.custom.SupplierDao;
import util.HibernateUtil;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(Object entity) {
        return false;
    }

    @Override
    public boolean update(Object entity) {
        return false;
    }

    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Object search(String id) {
        return null;
    }

    @Override
    public List<String> getIDs(){
        Session session= HibernateUtil.getSession();
        session.getTransaction().begin();
        List<String> ids=session.createNativeQuery("SELECT id FROM supplierentity",String.class).getResultList();
        session.close();
        return ids;
    }
}
