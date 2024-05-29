package com.uj.demo.demo.controllers;
import com.uj.demo.demo.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        model.addAttribute("username", ((User)session.getAttribute("user")).getName());
        return "profile"; // Ensure this matches the name of your Thymeleaf template
    }

}
