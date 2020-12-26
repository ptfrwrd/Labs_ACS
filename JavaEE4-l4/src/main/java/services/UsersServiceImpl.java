package services;

import dao.UsersDao;
import model.TableUpdate;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private UsersDao usersDao;
    private JmsTemplate jmsTemplate;

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Autowired
    @Qualifier(value = "jmsTemplate")
    public void setJmsTemplate(JmsTemplate template) {
        this.jmsTemplate = template;
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return usersDao.getAll();
    }

    @Override
    @Transactional
    public void create(User user) {
        usersDao.create(user);
        jmsTemplate.convertAndSend(
                "allUpdates",
                new TableUpdate("INSERT", user.getId(), "users", user.toString())
        );
    }

    @Override
    @Transactional
    public User getById(Long userId) {
        return usersDao.getById(userId);
    }

    @Override
    @Transactional
    public void update(User user) {
        usersDao.update(user);
        jmsTemplate.convertAndSend(
                "allUpdates",
                new TableUpdate("UPDATE", user.getId(), "users", user.toString())
        );
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        User user = getById(userId);
        usersDao.delete(user);
        jmsTemplate.convertAndSend(
                "allUpdates",
                new TableUpdate("DELETE", user.getId(), "users", user.toString())
        );
    }
}
