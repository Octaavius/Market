package com.uj.demo.demo.controllers;

import com.uj.demo.demo.services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.logging.log4j.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

    private final CartService cartService;
    private static final Logger logger = LogManager.getLogger(CartController.class);

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam String productName, @RequestParam String size, HttpSession session) {
        return cartService.addItemToCart(productName, size, session);
    }

    @PostMapping("/remove-from-cart")
    public String removeFromCart(@RequestParam String productName, @RequestParam String size, HttpSession session) {
        return cartService.removeItemFromCart(productName, size, session);
    }
}