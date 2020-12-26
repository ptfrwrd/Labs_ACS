package dao;

import model.User;

import java.util.List;

public interface UsersDao {
    List<User> getAll();

    void create(User user);

    User getById(Long userId);

    void update(User user);

    void delete(User user);
}
