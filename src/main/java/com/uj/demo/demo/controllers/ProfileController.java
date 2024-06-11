package com.uj.demo.demo.controllers;

import com.uj.demo.demo.services.ProductService;
import com.uj.demo.demo.services.ProfileService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    private final ProductService productService;
    private final ProfileService profileService;


    public ProfileController(ProductService productService, ProfileService profileService) {
        this.productService = productService;
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        return profileService.showProfile(model, session, productService);
    }
}
