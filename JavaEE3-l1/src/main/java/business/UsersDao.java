package business;

import model.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class UsersDao {
    private static final String PERSISTENCE_UNIT_NAME = "orders";

    @PersistenceContext
    private EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();

    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createQuery("select u from users u", User.class);
        return query.getResultList();
    }

    public void persist(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
}