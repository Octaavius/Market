package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.repositories.ProductRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) { return productRepository.save(product); }
}
