package com.example.TripHive.repository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String email, String password, Model model) {
        if (userService.login(email, password)) {
            model.addAttribute("message", "Login successful!");
            return "success";
        } else {
            model.addAttribute("message", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(String email, String password, String confirmPassword, Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("message", "Passwords do not match. Please try again.");
            return "signup";
        }

        if (userService.signup(email, password)) {
            model.addAttribute("message", "Sign up successful!");
            return "success";
        } else {
            model.addAttribute("message", "Failed to sign up. Please try again.");
            return "signup";
        }
    }
}
