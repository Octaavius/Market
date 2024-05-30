package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.Order;
import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.models.User;
import com.uj.demo.demo.services.OrderService;
import com.uj.demo.demo.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }
    @PostMapping
    public Order addOrder(@RequestBody Order order) { return orderService.saveOrder(order); }
    @GetMapping
    public String order(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Long> cart = (List<Long>) session.getAttribute("cart");
        List<Product> productsInCart = new ArrayList<>();
        if (cart != null) {
            for (Long id : cart) {
                productsInCart.add(productService.findProductById(id));
            }
        }
        Order order = new Order(user);
        order.setProducts(productsInCart);
        orderService.saveOrder(order);
        session.setAttribute("cart", new ArrayList<Long>());
        return "order";
    }
}

