package com.uj.demo.demo.services;

import com.uj.demo.demo.exceptions.UserNotExistsException;
import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.models.User;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {
    private static final Logger logger = LogManager.getLogger(ProfileService.class);

    public String showProfile(Model model, HttpSession session, ProductService productService) {
        logger.debug("Loading profile page");
        if (session.getAttribute("user") == null) {
            throw new UserNotExistsException();
        }

        model.addAttribute("username", ((User) session.getAttribute("user")).getName());

        logger.debug("Loading user's cart");
        List<Long> productsId = (List<Long>) session.getAttribute("cart");
        List<Product> products = new ArrayList<>();

        double sum = 0;
        boolean cartIsEmpty;
        if (productsId != null) {
            cartIsEmpty = false;
            for (Long productId : productsId) {
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
