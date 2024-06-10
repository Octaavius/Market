package com.uj.demo.demo.controllers;

import com.uj.demo.demo.exceptions.UserNotExistsException;
import com.uj.demo.demo.exceptions.WrongLoginOrPasswordException;
import com.uj.demo.demo.models.User;
import com.uj.demo.demo.services.LoginService;
import com.uj.demo.demo.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.*;
import org.springframework.web.servlet.ModelAndView;

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
        return loginService.loadLoginPage(model);
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, HttpSession session) throws NoSuchAlgorithmException {
        try {
            return loginService.authorizeUser(user, session, userService);
        } catch (WrongLoginOrPasswordException e) {
            throw e;
        }
    }

    /*@ExceptionHandler(WrongLoginOrPasswordException.class)
    public String handleWrongLoginOrPasswordException(WrongLoginOrPasswordException ex, Model model) {
        logger.error("Wrong login or password exception occurred", ex);
        model.addAttribute("user", new User());
        model.addAttribute("message", "Invalid username or password.");
        return "login";
    }*/

    @GetMapping("logout")
    public String logout(HttpSession session) {
        return loginService.unauthorizeUser(session);
    }


}

