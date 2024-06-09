package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.apache.logging.log4j.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    private static final Logger logger = LogManager.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseBody
    public List<Product> addProduct(@RequestBody List<Product> products) {
        logger.info("Adding products to user's cart: {}", products.toString());
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
