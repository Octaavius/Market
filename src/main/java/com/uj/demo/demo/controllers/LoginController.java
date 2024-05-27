package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.User;
import com.uj.demo.demo.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute("user") User user, Model model, HttpSession session) {
        User userFromDb = userService.findUser(user);
        if (userFromDb != null) {
            session.setAttribute(userFromDb.getId().toString(), userFromDb.getId());
            return "redirect:/";  // Redirect to home page
        } else {
            model.addAttribute("message", "Invalid username or password.");
            return "login";  // Stay on the login page
        }
    }
}

