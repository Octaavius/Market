package com.uj.demo.demo.services;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService {
    public String index(Model model, HttpSession session, ProductService productService){
        User user = (User) session.getAttribute("user");
        boolean loggedIn = (user != null);
        if(loggedIn) {
            model.addAttribute("greeting", "Hello, " + user.getName() + "!");
        }
        model.addAttribute("loggedIn", loggedIn);
        List<Product> products = productService.findAll().stream().filter(product -> product.getQuantity() > 0).distinct().collect(Collectors.toList());
        model.addAttribute("products", products);
        return "index";
    }
}
