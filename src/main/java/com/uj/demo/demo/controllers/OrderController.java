package com.uj.demo.demo.controllers;

import com.uj.demo.demo.exceptions.UserNotExistsException;
import com.uj.demo.demo.services.OrderService;
import com.uj.demo.demo.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    private final static Logger logger = LogManager.getLogger(OrderController.class);

    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping
    public String order(HttpSession session, Model model) {
        try {
            return orderService.makeOrder(session, model, productService);
        } catch (UserNotExistsException e) {
            throw e;
        }
    }
}

