package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseBody
    public List<Product> addProduct(@RequestBody List<Product> products) {
        List<Product> addedProducts = new ArrayList<>();
        for (Product product : products) {
            addedProducts.add(productService.save(product));
        }
        return addedProducts;
    }

    @GetMapping("/{name}")
    @ResponseBody
    public List<Product> getProductsByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @GetMapping()
    @ResponseBody
    public List<Product> getAllDifferentProducts() {
        List<Product> newProducts = productService.getAll();
        return newProducts.stream().distinct().collect(Collectors.toList());
    }
}
