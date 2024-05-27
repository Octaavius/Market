package com.uj.demo.demo.services;

import com.uj.demo.demo.models.Order;
import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.repositories.OrderRepository;
import com.uj.demo.demo.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Order saveOrder(Order order) {
        List<Product> existingProducts = new ArrayList<>();
        for (Product product : order.getProducts()) {
            Product existingProduct = productRepository.findByModel(product.getModel());
            if (existingProduct != null) {
                existingProducts.add(existingProduct);
            } else {
                existingProducts.add(product);
            }
        }
        order.getProducts().clear();
        order.getProducts().addAll(existingProducts);
        return orderRepository.save(order);
    }
}