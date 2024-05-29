package com.uj.demo.demo.controllers;
import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.models.User;
import com.uj.demo.demo.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    private final ProductService productService;

    public ProfileController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        model.addAttribute("username", ((User)session.getAttribute("user")).getName());

        List<Long> productsId = (List<Long>) session.getAttribute("cart");

        List<Product> products = new ArrayList<>();

        if(productsId != null) {
            for(Long productId: productsId){
                Product product = productService.findProductById(productId);
                products.add(product);
            }
        }

        model.addAttribute("productsInCart", products);

        return "profile"; // Ensure this matches the name of your Thymeleaf template
    }

}
