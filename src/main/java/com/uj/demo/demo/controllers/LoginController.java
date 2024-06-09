package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.User;
import com.uj.demo.demo.services.LoginService;
import com.uj.demo.demo.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.*;

@Controller
public class LoginController {
    private final UserService userService;
    private final LoginService loginService;

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    public LoginController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        logger.info("Loading login page");
        return loginService.loadLoginPage(model);
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model, HttpSession session) throws NoSuchAlgorithmException {
        logger.info("Processing login request");
        return loginService.authorizeUser(user, model, session, userService);
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        logger.info("Processing logout request");
        return loginService.unauthorizeUser(session);
    }


}

