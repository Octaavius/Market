package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.services.ProductService;
import jakarta.servlet.http.HttpSession;
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

    @ResponseBody
    public List<Product> getProductsByName(String name) {
        return productService.findByName(name);
    }

    public List<String> getAllSizes(String name){
        List<Product> products = getProductsByName(name);
        List<String> sizes = new ArrayList<>();
        for (Product product : products){
            if(product.getQuantity() > 0) {
                sizes.add(product.getSize());
            }
        }
        return sizes;
    }

    @GetMapping("{name}")
    public String product(@PathVariable("name") String name, Model model, HttpSession session) {
        if(getProductsByName(name).size() > 0) {
            model.addAttribute("product", getProductsByName(name).get(0));
            model.addAttribute("sizes", getAllSizes(name).stream().sorted().collect(Collectors.toList()));
        }
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        return "product";
    }

    @GetMapping()
    @ResponseBody
    public List<Product> getAllDifferentProducts() {
        List<Product> newProducts = productService.findAll();
        return newProducts.stream().distinct().collect(Collectors.toList());
    }
}
