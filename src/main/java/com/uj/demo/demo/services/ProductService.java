package com.uj.demo.demo.services;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.models.User;
import com.uj.demo.demo.repositories.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveOrder(Product product) {
        Product exsistingProduct = productRepository.findByModel(product.getModel());
        if(exsistingProduct != null){
            return null;
        }
        return productRepository.save(product);
    }
}