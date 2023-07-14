package com.example.TripHive.repository;

import java.util.Scanner;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Scanner;

@Component
public class LoginSystem {
    private static final int MAX_LOGIN_ATTEMPTS = 3;

    private final UserService userService;
    private final Scanner scanner;

    public LoginSystem(UserService userService) {
        this.userService = new UserService();
        this.scanner = new Scanner(System.in);
    }


    public boolean login(String email, String password) {
        int attempts = 0;
        boolean accessGranted = false;
        while (attempts < MAX_LOGIN_ATTEMPTS) {
//            String email = input("Enter email: ");
//            String password = input("Enter password: ");
            if (userService.login(email, password)) {
                System.out.println("Access granted");
                accessGranted = true;
                break;
            } else {
                System.out.println("Invalid email or password");
                attempts++;
            }
        }
        if (!accessGranted) {
            System.out.println("You have reached the login limit");
        }
        return userService.login(email, password);
    }

    private void signup() {
        String email = input("Enter a new email: ");
        String password = input("Enter a new password: ");
        String confirmPassword = input("Confirm password: ");
        if (password.equals(confirmPassword)) {
            if (userService.signup(email, password)) {
                System.out.println("Sign up successful!");
            } else {
                System.out.println("Failed to sign up. Please try again.");
            }
        } else {
            System.out.println("Passwords do not match. Please try again.");
        }
    }

    private String input(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}

