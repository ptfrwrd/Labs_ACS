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
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from users").list();
    }

    @Override
    public void saveUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
    }
}