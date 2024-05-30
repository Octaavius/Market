package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.Order;
import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.models.User;
import com.uj.demo.demo.services.OrderService;
import com.uj.demo.demo.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String order(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Long> cart = (List<Long>) session.getAttribute("cart");
        List<Product> productsInCart = new ArrayList<>();
        if (cart != null) {
            boolean hasNonAvaliableItems = false;
            for (Long id : cart) {
                Product product = productService.findProductById(id);
                if(product.getQuantity() == 0){
                    hasNonAvaliableItems = true;
                    continue;
                }
                productsInCart.add(product);
            }
            if(hasNonAvaliableItems || productsInCart.size() == 0){
                model.addAttribute("productsInCart", productsInCart);
                return "redirect:/profile";
            }
        }

        for (Product product : productsInCart){
            product.decreaseQuantity(1);
            productService.updateProduct(product.getId(), product);
        }

        Order order = new Order(user);
        order.setProducts(productsInCart);
        Order savedOrder = orderService.saveOrder(order);
        Long orderId = savedOrder.getId();
        session.setAttribute("cart", new ArrayList<Long>());
        return "order";
    }
}

