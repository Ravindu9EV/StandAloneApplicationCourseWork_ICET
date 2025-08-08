package repository.custom.impl;

import entity.EmployeeEntity;
import entity.ItemEntity;
import entity.StockEntity;
import entity.StockID;

import org.hibernate.Session;
import repository.custom.StockDao;
import util.HibernateUtil;

import java.util.List;
import java.util.stream.Collectors;

public class StockDaoImpl implements StockDao {
    @Override
    public boolean save(StockEntity entity) {
        try {
            Session session= HibernateUtil.getSession();
            session.getTransaction().begin();
            session.persist(entity);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(StockEntity entity) {
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();

        session.update(entity);
        session.getTransaction().commit();
        session.close();

//        System.out.println("For Update"+entity);
//        Session session=HibernateUtil.getSession();
//        session.getTransaction().begin();
//
//        boolean b=session.createNativeQuery("UPDATE itementity SET name='"+entity.getName()+"',price='"+entity.getPrice()+"', quantity='"+entity.getQuantity()+"', discount='"+entity.getDiscount()+"',brand='"+entity.getBrand()+"', size_id='"+entity.getSizeEntity().getId()+"',supplier_id='"+entity.getSupplierEntity().getId()+"' WHERE id='"+entity.getId()+"'", EmployeeEntity.class).executeUpdate()>0;
//        if(b){
//            b= dao.update(new StockEntity(new StockID(entity.getId(),entity.getSizeEntity().getId()).toString(), entity.getQuantity() ));
//        }
//        session.getTransaction().commit();
//        session.close();
//        return b;
        return false;
    }

    @Override
    public List<StockEntity> findAll() {
        Session session=HibernateUtil.getSession();
        return session.createNativeQuery("Select * from stockentity", StockEntity.class).stream().collect(Collectors.toList());

    }

    @Override
    public boolean delete(String id) {

        return false;
    }

    @Override
    public StockEntity search(String id) {
        return null;
    }
    public boolean delete(StockID id) {
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        StockEntity entity=search(id);
        if(entity!=null) {
            session.remove(entity);
            return true;
        }
        return false;
    }

    @Override
    public StockEntity search(StockID id) {
        Session session=HibernateUtil.getSession();
        StockEntity entity=session.createNativeQuery("Select * from stockentity where value='"+id+"'", StockEntity.class).getSingleResult();
        session.close();
        return entity;
    }

    //----------update stock quantity---------
    public boolean update(StockID id,Integer quantity){
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        session.createNativeQuery("UPDATE stockentity SET stockCount=stockCount-"+quantity+" WHERE stockID='"+id+"'", StockEntity.class);
        session.getTransaction().commit();
        session.close();
        return true;

    }
}
