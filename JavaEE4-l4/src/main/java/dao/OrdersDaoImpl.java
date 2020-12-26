package dao;

import model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersDaoImpl implements OrdersDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Order> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from orders order by id").list();
    }

    @Override
    public void create(Order order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(order);
        session.flush();
    }

    @Override
    public Order getById(Long orderId) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Order) session.get(Order.class, orderId);
    }

    @Override
    public void update(Order order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(order);
        session.flush();
    }

    @Override
    public void delete(Order order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(order);
    }
}