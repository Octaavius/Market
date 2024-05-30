package com.uj.demo.demo.controllers;
import com.uj.demo.demo.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import com.uj.demo.demo.services.ProductService;
import com.uj.demo.demo.models.Product;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        //boolean loggedIn = (Session.currentSessionId != 0);
        boolean loggedIn = (user != null);
        if(loggedIn) {
            model.addAttribute("greeting", "Hello, " + user.getName() + "!");
        }
        model.addAttribute("loggedIn", loggedIn);
        List<Product> products = productService.findAll().stream().filter(product -> product.getQuantity() > 0).distinct().collect(Collectors.toList());
        model.addAttribute("products", products);
        return "index"; // Ensure this matches the name of your Thymeleaf template
    }

}
