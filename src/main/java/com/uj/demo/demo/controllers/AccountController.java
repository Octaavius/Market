package com.uj.demo.demo.controllers;
import org.springframework.stereotype.Controller;
import com.uj.demo.demo.services.ProductService;
import com.uj.demo.demo.models.Session;
import com.uj.demo.demo.models.Product;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AccountController {

    @GetMapping("/account")
    public String index() {
        return "account"; // Ensure this matches the name of your Thymeleaf template
    }

}
