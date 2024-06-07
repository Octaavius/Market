package com.uj.demo.demo.services;

import com.uj.demo.demo.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class LoginService {
    private final String SALT = "salt";

    public String loadLoginPage(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    public String authorizeUser(User user, Model model, HttpSession session, UserService userService) throws NoSuchAlgorithmException {
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

    public String unauthorizeUser(HttpSession session){
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
