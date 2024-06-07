package com.uj.demo.demo.services;

import com.uj.demo.demo.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.NoSuchAlgorithmException;

@Service
public class RegistrationService {
    private String SALT = "salt";

    public String loadSignUpPage(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    public String addNewUser(User user, Model model, HttpSession session, UserService userService) throws NoSuchAlgorithmException {
        String password = user.getPassword();
        user.setPassword(LoginService.getMd5(LoginService.getSHA(password) + SALT));
        User newUser = userService.saveUser(user);
        if (newUser != null) {
            session.setAttribute("user", newUser);
            return "redirect:/";  // Redirect to home page
        } else {
            model.addAttribute("message", "Such username already exists.");
            return "signup";  // Stay on the login page
        }
    }
}
