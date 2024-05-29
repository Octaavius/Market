package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.Order;
import com.uj.demo.demo.repositories.OrderRepository;
import com.uj.demo.demo.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order addOrder(@RequestBody Order order) { return orderService.saveOrder(order); }

    @GetMapping
    public String order(){
        return "order";
    }
}

