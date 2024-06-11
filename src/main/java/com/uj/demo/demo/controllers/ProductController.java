package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return productService.addProducts(products);
    }

    @GetMapping("/name")
    @ResponseBody
    public List<Product> getProductsByName(@RequestParam("name") String name) {
        return productService.findByName(name);
    }

    @GetMapping("/sizes")
    @ResponseBody
    public List<String> getAllSizes(@RequestParam("name") String name) {
        return productService.getAllSizes(name);
    }

    @GetMapping("/{name}")
    public String product(@PathVariable("name") String name, Model model, HttpSession session) {
        return productService.productInfo(name, model, session);
    }

    @GetMapping()
    @ResponseBody
    public List<Product> getAllDifferentProducts() {
        return productService.getAllDifferentProducts();
    }
}
