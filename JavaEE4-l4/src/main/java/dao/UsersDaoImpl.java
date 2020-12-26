package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersDaoImpl implements UsersDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from users order by id").list();
    }

    @Override
    public void create(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        session.flush();
    }

    @Override
    public User getById(Long userId) {
        Session session = this.sessionFactory.getCurrentSession();
        return (User) session.get(User.class, userId);
    }

    @Override
    public void update(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(user);
        session.flush();
    }

    @Override
    public void delete(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(user);
    }
}