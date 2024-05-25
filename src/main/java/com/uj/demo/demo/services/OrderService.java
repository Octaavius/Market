package com.uj.demo.demo.services;

import com.uj.demo.demo.models.Order;
import com.uj.demo.demo.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {return orderRepository.save(order);}
}
