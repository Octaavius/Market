package com.uj.demo.demo.services;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.repositories.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public String productInfo(String name, Model model, HttpSession session) {
        List<Product> products = findByName(name);
        if (products.size() > 0) {
            model.addAttribute("product", products.get(0));
            model.addAttribute("sizes", getAllSizes(name).stream().sorted().collect(Collectors.toList()));
        }
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        return "product";
    }

    public List<Product> getAllDifferentProducts() {
        List<Product> newProducts = findAll();
        return newProducts.stream().distinct().collect(Collectors.toList());
    }

    public List<String> getAllSizes(String name) {
        List<Product> products = findByName(name);
        List<String> sizes = new ArrayList<>();
        for (Product product : products) {
            if (product.getQuantity() > 0) {
                sizes.add(product.getSize());
            }
        }
        return sizes;
    }

    public List<Product> addProducts(List<Product> products){
        List<Product> addedProducts = new ArrayList<>();
        for (Product product : products) {
            addedProducts.add(save(product));
        }
        return addedProducts;
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

    public Product updateProduct(Long productId, Product updatedProductDetails) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setType(updatedProductDetails.getType());
            existingProduct.setBrand(updatedProductDetails.getBrand());
            existingProduct.setModel(updatedProductDetails.getModel());
            existingProduct.setColor(updatedProductDetails.getColor());
            existingProduct.setSex(updatedProductDetails.getSex());
            existingProduct.setSize(updatedProductDetails.getSize());
            existingProduct.setQuantity(updatedProductDetails.getQuantity());
            existingProduct.setPrice(updatedProductDetails.getPrice());

            // Save the updated product back to the database
            return productRepository.save(existingProduct);
        } else {
            // Handle the case where the product is not found
            throw new RuntimeException("Product not found with id " + productId);
        }
    }
}