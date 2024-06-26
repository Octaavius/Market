package com.uj.demo.demo.controllers;

import com.uj.demo.demo.services.HomeService;
import com.uj.demo.demo.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final ProductService productService;
    private final HomeService homeService;


    public HomeController(ProductService productService, HomeService homeService) {
        this.productService = productService;
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        return homeService.index(model, session, productService);
    }

}
