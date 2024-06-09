package com.uj.demo.demo.services;

import com.uj.demo.demo.exceptions.UserAlreadyExistsException;
import com.uj.demo.demo.exceptions.WrongPasswordFormatException;
import com.uj.demo.demo.models.User;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.NoSuchAlgorithmException;

@Service
public class RegistrationService {
    private String SALT = "salt";

    private static final Logger logger = LogManager.getLogger(RegistrationService.class);

    public String loadSignUpPage(Model model) {
        logger.info("Loading Sign Up Page");
        model.addAttribute("user", new User());
        return "signup";
    }

    public String addNewUser(User user, HttpSession session, UserService userService) throws NoSuchAlgorithmException {
        logger.debug("Getting entered user info...");
        logger.info("Adding new user with username: {}", user.getLogin());
        String password = user.getPassword();

        if(password.length() < 8){
            throw new WrongPasswordFormatException();
        }

        user.setPassword(LoginService.getMd5(LoginService.getSHA(password) + SALT));

        User newUser = userService.saveUser(user);
        if (newUser != null) {
            session.setAttribute("user", newUser);
            return "redirect:/";  // Redirect to home page
        } else {
            throw new UserAlreadyExistsException();
        }
    }
}
