package dao;

import model.TableUpdate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TableUpdatesDaoImpl implements TableUpdatesDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TableUpdate> getAllUpdates() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from table_updates order by id desc").list();
    }

    @Override
    public void insertUpdate(TableUpdate update) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(update);
        session.flush();
    }
}