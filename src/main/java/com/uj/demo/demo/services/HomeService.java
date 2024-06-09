package com.uj.demo.demo.services;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.*;

@Service
public class HomeService {

    private static final Logger logger = LogManager.getLogger(HomeService.class);

    public String index(Model model, HttpSession session, ProductService productService){
        logger.info("Loading index page...");
        logger.debug("Checking if user is logged in");
        User user = (User) session.getAttribute("user");
        boolean loggedIn = (user != null);
        if(loggedIn) {
            model.addAttribute("greeting", "Hello, " + user.getName() + "!");
        }
        model.addAttribute("loggedIn", loggedIn);
        logger.info("Loading all products");
        List<Product> products = productService.findAll().stream().filter(product -> product.getQuantity() > 0).distinct().collect(Collectors.toList());
        model.addAttribute("products", products);
        logger.info("Index page is loaded");
        return "index";
    }
}
