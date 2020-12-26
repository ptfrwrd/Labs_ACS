package dao;

import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductsDaoImpl implements ProductsDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from products order by id").list();
    }

    @Override
    public void create(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(product);
        session.flush();
    }

    @Override
    public Product getById(Long productId) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Product) session.get(Product.class, productId);
    }

    @Override
    public void update(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(product);
        session.flush();
    }

    @Override
    public void delete(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(product);
    }
}