package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.User;
import com.uj.demo.demo.services.SessionService;
import com.uj.demo.demo.services.UserService;
import com.uj.demo.demo.models.Session;
import jakarta.persistence.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("login")
public class LoginController {

    private final UserService userService;
    private final SessionService sessionService;

    public LoginController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @GetMapping
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute("user") User user, Model model) {
        User userFromDb = userService.findUser(user);
        if (userFromDb != null) {
            Session currentSession = sessionService.findByUser(userFromDb.getId());
            //sessionService.actualizeSession(currentSession);
            Session.currentSessionId = currentSession.getId();
            return "redirect:/";  // Redirect to home page
        } else {
            model.addAttribute("message", "Invalid username or password.");
            return "login";  // Stay on the login page
        }
    }
}

