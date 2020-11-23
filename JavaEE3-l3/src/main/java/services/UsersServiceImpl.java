package services;

import dao.UsersDao;
import model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private UsersDao usersDao;

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return usersDao.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        usersDao.saveUser(user);
    }
}
