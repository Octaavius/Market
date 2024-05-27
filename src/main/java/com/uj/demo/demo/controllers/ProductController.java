package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @PostMapping
//    public Product addProduct(@RequestBody Product product) { return productService.save(product); }

    @PostMapping
    public List<Product> addProduct(@RequestBody List<Product> products) {
        List<Product> addedProducts = new ArrayList<>();
        for(Product product: products){
            addedProducts.add((productService.save(product)));
        }
        return addedProducts;
    }

    @GetMapping("/{name}")
    public List<Product> getProductsByName(@PathVariable String name) {
        return productService.findByName(name);
    }
}
