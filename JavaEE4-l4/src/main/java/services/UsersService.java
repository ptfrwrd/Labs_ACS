package services;

import model.User;

import java.util.List;

public interface UsersService {
    List<User> getAll();

    void create(User user);

    User getById(Long userId);

    void update(User user);

    void delete(Long userId);
}
