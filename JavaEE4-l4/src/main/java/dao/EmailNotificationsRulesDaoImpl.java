package dao;

import model.EmailNotificationsRule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmailNotificationsRulesDaoImpl implements EmailNotificationsRulesDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EmailNotificationsRule> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from email_notifications_rules order by id").list();
    }

    @Override
    public void create(EmailNotificationsRule emailNotificationsRule) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(emailNotificationsRule);
        session.flush();
    }

    @Override
    public EmailNotificationsRule getById(Long emailNotificationsRuleId) {
        Session session = this.sessionFactory.getCurrentSession();
        return (EmailNotificationsRule) session.get(EmailNotificationsRule.class, emailNotificationsRuleId);
    }

    @Override
    public void update(EmailNotificationsRule emailNotificationsRule) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(emailNotificationsRule);
        session.flush();
    }

    @Override
    public void delete(EmailNotificationsRule emailNotificationsRule) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(emailNotificationsRule);
    }
}