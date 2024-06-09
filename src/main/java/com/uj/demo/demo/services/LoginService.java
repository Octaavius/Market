package com.uj.demo.demo.services;

import com.uj.demo.demo.exceptions.WrongLoginOrPasswordException;
import com.uj.demo.demo.models.User;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class LoginService {
    private final String SALT = "salt";

    private static final Logger logger = LogManager.getLogger(LoginService.class);

    public String loadLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    public String authorizeUser(User user, HttpSession session, UserService userService) throws NoSuchAlgorithmException {
        logger.info("Processing login request...");
        logger.debug("Getting entered user info...");
        String password = user.getPassword();
        user.setPassword(getMd5(getSHA(password) + SALT));
        logger.debug("Finding such user in database");
        User userFromDb = userService.findUser(user);
        if (userFromDb != null) {
            logger.info("User found in database. Authorizing user");
            session.setAttribute("user", userFromDb);
            return "redirect:/";
        } else {
            throw new WrongLoginOrPasswordException("No such user found");
        }
    }

    public String unauthorizeUser(HttpSession session) {
        logger.debug("Unauthorizing user");
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
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}
