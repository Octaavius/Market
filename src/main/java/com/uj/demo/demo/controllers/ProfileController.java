package com.uj.demo.demo.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile() {
        return "profile"; // Ensure this matches the name of your Thymeleaf template
    }

}
