package com.uj.demo.demo.services;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Long getId(String model, String size){
        List<Product> products = findByName(model);
        for(Product product: products){
            if(product.getSize().equals(size)){
                return product.getId();
            }
        }
        return (long) -1;
    }

    public List<Product> findAll() { return productRepository.findAll(); }


    public Product findProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }
}