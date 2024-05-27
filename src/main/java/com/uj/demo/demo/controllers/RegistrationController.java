package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.User;
import com.uj.demo.demo.models.Session;
import com.uj.demo.demo.services.SessionService;
import com.uj.demo.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("signup")
public class RegistrationController {

    private final UserService userService;
    private final SessionService sessionService;

    public RegistrationController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @GetMapping
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping
    public String signup(@ModelAttribute("user") User user, Model model) {
        User newUser = userService.saveUser(user);
        if (newUser != null) {
            Session session = sessionService.create(user);
            Session.currentSessionId = session.getId();
            //model.addAttribute("message", "Sign up successful!");
            return "redirect:/";  // Redirect to home page
        } else {
            model.addAttribute("message", "Such username already exists.");
            return "signup";  // Stay on the login page
        }
    }
}

