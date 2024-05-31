package com.uj.demo.demo.services;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final ProductService productService;

    public CartService(ProductService productService) {
        this.productService = productService;
    }

    public void addItemToCart(String productName, String size, HttpSession session){
        List<Long> cart = (List<Long>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        Long productId = productService.getId(productName, size);
        cart.add(productId);
        session.setAttribute("cart", cart);
    }

    public void removeItemFromCart(String productName, String size, HttpSession session){
        List<Long> cart = (List<Long>) session.getAttribute("cart");
        if (cart == null) {
            return;
        }
        Long productId = productService.getId(productName, size);
        cart.remove(productId);
        session.setAttribute("cart", cart);
    }
}