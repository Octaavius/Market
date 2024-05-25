package com.uj.demo.demo.controllers;

import com.uj.demo.demo.services.LoginService;
import com.uj.demo.demo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login() { return "login"; }

//    @PostMapping
//    public User addUser(@RequestBody User user) { return loginService.saveUser(user); }
}

