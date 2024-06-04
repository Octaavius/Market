package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.User;
import com.uj.demo.demo.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Controller
public class LoginController {
    private final String SALT = "salt";

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model, HttpSession session) throws NoSuchAlgorithmException {
        String password = user.getPassword();
        user.setPassword(getMd5(getSHA(password) + SALT));
        User userFromDb = userService.findUser(user);
        if (userFromDb != null) {
            session.setAttribute("user", userFromDb);
            return "redirect:/";  // Redirect to home page
        } else {
            model.addAttribute("message", "Invalid username or password.");
            return "login";  // Stay on the login page
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    public static String getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = md.digest(input.getBytes());
        return toHexString(messageDigest);
    }

    public static String getMd5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        return toHexString(messageDigest);
    }

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}

