package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void createUser(String username, String email) throws SQLException {
        User user = new User(0, username, email);
        userService.addUser(user);
        System.out.println("User created");
    }

    public void listUsers() throws SQLException {
        List<User> users = (List<User>) userService.getUsers();
        users.forEach(user -> System.out.println(user.toString()));
    }

    public void editUser(int id, String username, String email) throws SQLException {
        User user = new User(id, username, email);
        userService.updateUser(user);
        System.out.println("User updated successfully.");
    }

    public void deleteUser(int id) throws SQLException {
        userService.deleteUser(id);
    }
}
