package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.User;
import com.uj.demo.demo.services.LoginService;
import com.uj.demo.demo.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.NoSuchAlgorithmException;

@Controller
public class LoginController {
    private final UserService userService;
    private final LoginService loginService;


    public LoginController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        return loginService.loadLoginPage(model);
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, HttpSession session) throws NoSuchAlgorithmException {
        return loginService.authorizeUser(user, session, userService);
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        return loginService.unauthorizeUser(session);
    }


}

