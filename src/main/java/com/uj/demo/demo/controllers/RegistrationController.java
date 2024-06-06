package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.User;
import com.uj.demo.demo.services.UserService;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("signup")
public class RegistrationController {
    private final String SALT = "salt";

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping
    public String signup(@ModelAttribute("user") User user, Model model, HttpSession session) throws NoSuchAlgorithmException {
        String password = user.getPassword();
        user.setPassword(LoginController.getMd5(LoginController.getSHA(password) + SALT));
        User newUser = userService.saveUser(user);
        if (newUser != null) {
            session.setAttribute("user", newUser);
            return "redirect:/";  // Redirect to home page
        } else {
            model.addAttribute("message", "Such username already exists.");
            return "signup";  // Stay on the login page
        }
    }
}

