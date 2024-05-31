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
        if(session.getAttribute("user") == null){
            return "redirect:/login";
        }

        model.addAttribute("username", ((User)session.getAttribute("user")).getName());

        List<Long> productsId = (List<Long>) session.getAttribute("cart");

        List<Product> products = new ArrayList<>();

        double sum = 0;
        boolean cartIsEmpty;
        if(productsId != null) {
            cartIsEmpty = false;
            for(Long productId: productsId){
                Product product = productService.findProductById(productId);
                products.add(product);
                sum += product.getPrice();
            }
        } else {
            cartIsEmpty = true;
        }

        model.addAttribute("cartIsEmpty", cartIsEmpty);
        model.addAttribute("productsInCart", products);
        model.addAttribute("sumPrice", sum);

        return "profile"; // Ensure this matches the name of your Thymeleaf template
    }

}
