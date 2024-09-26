package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) throws SQLException {
        this.userRepository.createUser(user);
    }

    public void updateUser(User user) throws SQLException {
        this.userRepository.updateUser(user);
    }

    public void deleteUser(int id) throws SQLException {
        this.userRepository.deleteUser(id);
    }

    public List<User> getUsers() throws SQLException {
        return this.userRepository.getAllUsers();
    }
}
