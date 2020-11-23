package dao;

import model.User;

import java.util.List;

public interface UsersDao {
    List<User> getAllUsers();

    void saveUser(User user);
}
