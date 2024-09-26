package org.example;

import org.example.controller.UserController;
import org.example.repository.UserRepository;
import org.example.service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
//        set DB_URL=jdbc:mysql://localhost:3306/springboot
//        set DB_USER=root
//        set DB_PASSWORD=your_password
//        System.out.println(System.getenv("DB_URL"));

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/springboot", "root", "");

            UserRepository userRepository = new UserRepository(connection);
            UserService userService = new UserService(userRepository);
            UserController userController = new UserController(userService);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Enter operation (create, update, delete, list, exit): ");
                String operation = scanner.nextLine().toLowerCase();

                if (operation.equals("exit")) {
                    System.out.println("Exiting the application...");
                    break;
                }

                switch (operation) {
                    case "create":
                        System.out.println("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.println("Enter email: ");
                        String email = scanner.nextLine();

                        if (validateUsername(username) && validateEmail(email)) {
                            userController.createUser(username, email);
                        } else {
                            System.out.println("Invalid username or email, try again.");
                        }
                        break;
                    case "update":
                        System.out.println("Enter user ID to update: ");
                        int userID = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter username: ");
                        String updateuserName = scanner.nextLine();
                        System.out.println("Enter email: ");
                        String updateemail = scanner.nextLine();

                        if (validateUsername(updateuserName) && validateEmail(updateemail)) {
                            userController.editUser(userID, updateuserName, updateemail);
                        } else {
                            System.out.println("Invalid username or email, try again.");
                        }
                        break;
                    case "delete":
                        System.out.println("Enter user ID to delete: ");
                        int deleteuserID = Integer.parseInt(scanner.nextLine());
                        userController.deleteUser(deleteuserID);
                        break;
                    case "list":
                        userController.listUsers();
                        break;
                    default:
                        System.out.println("Invalid operation, try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean validateUsername(String username) {
        return username != null && username.length() > 3;
    }

    public static boolean validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email != null && email.matches(emailRegex);
    }
}