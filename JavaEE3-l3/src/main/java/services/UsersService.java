package services;

import model.User;

import java.util.List;

public interface UsersService {
    List<User> getAllUsers();

    void saveUser(User user);
}
