package com.uj.demo.demo.controllers;

import com.uj.demo.demo.services.CartService;
import com.uj.demo.demo.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam String productName, @RequestParam String size, HttpSession session) {
        cartService.addItemToCart(productName, size, session);
        return "redirect:/profile";
    }

    @PostMapping("/remove-from-cart")
    public String removeFromCart(@RequestParam String productName, @RequestParam String size,  HttpSession session) {
        cartService.removeItemFromCart(productName, size, session);
        return "profile";
    }
}