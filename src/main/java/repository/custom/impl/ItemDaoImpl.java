package repository.custom.impl;

import entity.EmployeeEntity;
import entity.ItemEntity;
import entity.StockEntity;
import entity.StockID;
import org.hibernate.Session;
import repository.DaoFactory;
import repository.custom.ItemDao;
import util.DaoType;
import util.HibernateUtil;

import java.util.List;
import java.util.stream.Collectors;

public class ItemDaoImpl implements ItemDao {
    StockDaoImpl dao= DaoFactory.getDaoType(DaoType.STOCK);
    @Override
    public boolean save(ItemEntity entity) {
        Session session=HibernateUtil.getSession();
        try{

            session.getTransaction().begin();
            session.persist(entity);
            if(dao.save(new StockEntity(new StockID(entity.getId(), entity.getSizeEntity().getId()).toString(),entity.getQuantity()))){
                session.getTransaction().commit();
                session.close();
                return true;
            }


        }catch (Exception e){
            return false;
        }finally {
            session.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public boolean update(ItemEntity entity) {
        System.out.println("For Update"+entity);
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();

        boolean b=session.createNativeQuery("UPDATE itementity SET name='"+entity.getName()+"',price='"+entity.getPrice()+"', quantity='"+entity.getQuantity()+"', discount='"+entity.getDiscount()+"',brand='"+entity.getBrand()+"', size_id='"+entity.getSizeEntity().getId()+"',supplier_id='"+entity.getSupplierEntity().getId()+"' WHERE id='"+entity.getId()+"'",EmployeeEntity.class).executeUpdate()>0;
        if(b){
           b= dao.update(new StockEntity(new StockID(entity.getId(),entity.getSizeEntity().getId()).toString(), entity.getQuantity() ));
        }
        session.getTransaction().commit();
        session.close();
        return b;
    }

    @Override
    public List<ItemEntity> findAll() {
        Session session=HibernateUtil.getSession();
        return session.createNativeQuery("Select * from itementity", ItemEntity.class).stream().collect(Collectors.toList());

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
    public ItemEntity search(String id) {
        Session session=HibernateUtil.getSession();
        ItemEntity entity=session.find(ItemEntity.class,id);
        session.close();
        return entity;
    }

    @Override
    public List<String> getIDs() {
        Session session= HibernateUtil.getSession();
        List<String> ids=session.createNativeQuery("SELECT id FROM itementity",String.class).getResultList();
        session.close();
        return ids;
    }

    @Override
    public boolean updateStock(String id,Integer bought) {
        Session session=HibernateUtil.getSession();
        session.createNativeQuery("UPDATE itementity SET quantity=quantity-'"+bought+"' WHERE id='"+id+"'");
        session.close();
        return true;
    }
}
