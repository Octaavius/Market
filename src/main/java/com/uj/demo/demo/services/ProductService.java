package com.uj.demo.demo.services;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        List<Product> existingProducts = productRepository.findByModel(product.getModel());
        for (Product exProduct : existingProducts){
            if (exProduct.getSize().equals(product.getSize())) {
                return null;
            }
        }
        return productRepository.save(product);
    }

    public List<Product> findByName(String name) {
        return productRepository.findByModel(name);
    }
}