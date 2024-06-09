package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.User;
import com.uj.demo.demo.services.LoginService;
import com.uj.demo.demo.services.RegistrationService;
import com.uj.demo.demo.services.UserService;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.*;

@Controller
@RequestMapping("signup")
public class RegistrationController {
    private final UserService userService;
    private final RegistrationService registrationService;

    private static final Logger logger = LogManager.getLogger(RegistrationController.class);

    public RegistrationController(UserService userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @GetMapping
    public String signup(Model model) {
        logger.info("Loading sign up page");
        return registrationService.loadSignUpPage(model);
    }

    @PostMapping
    public String signup(@ModelAttribute("user") User user, Model model, HttpSession session) throws NoSuchAlgorithmException {
        logger.info("Adding new user with username: {}", user.getLogin());
        return registrationService.addNewUser(user, model, session, userService);
    }
}

