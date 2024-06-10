package com.uj.demo.demo.services;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.repositories.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private static final Logger logger = LogManager.getLogger(ProductService.class);

    public Product save(Product product) {
        logger.debug("Saving product {}", product.toString());
        List<Product> existingProducts = productRepository.findByModel(product.getModel());
        for (Product exProduct : existingProducts) {
            if (exProduct.getSize().equals(product.getSize())) {
                logger.warn("Trying to save product {} with already saved size. Ignoring save", product.toString());
                //if the product is the same as in the db, just add quantity
                return null;
            }
        }
        logger.debug("Successfully saved product {}", product.toString());
        return productRepository.save(product);
    }

    public String productInfo(String name, Model model, HttpSession session) {
        logger.info("Loading {} page", name);
        logger.debug("Getting product info for {}", name);
        List<Product> products = findByName(name);
        if (products.size() > 0) {
            logger.debug("Found {} products", products.size());
            model.addAttribute("product", products.get(0));
            model.addAttribute("sizes", getAllSizes(name).stream().sorted().collect(Collectors.toList()));
        } else {
            logger.warn("No product found for name {}", name);
        }
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        return "product";
    }

    public List<Product> getAllDifferentProducts() {
        logger.debug("Getting all different products");
        List<Product> newProducts = findAll();
        return newProducts.stream().distinct().collect(Collectors.toList());
    }

    public List<String> getAllSizes(String name) {
        logger.debug("Getting all different sizes for {}", name);
        List<Product> products = findByName(name);
        List<String> sizes = new ArrayList<>();
        for (Product product : products) {
            if (product.getQuantity() > 0) {
                sizes.add(product.getSize());
            }
        }
        return sizes;
    }

    public List<Product> addProducts(List<Product> products) {
        logger.debug("Adding products to database: {}", products.toString());
        List<Product> addedProducts = new ArrayList<>();
        for (Product product : products) {
            addedProducts.add(save(product));
        }
        return addedProducts;
    }

    public List<Product> findByName(String name) {
        logger.debug("Finding product by name {}", name);
        return productRepository.findByModel(name);
    }

    public Long getId(String model, String size) {
        logger.debug("Getting product id for {}", model);
        List<Product> products = findByName(model);
        for (Product product : products) {
            if (product.getSize().equals(size)) {
                return product.getId();
            }
        }
        return (long) -1;
    }

    public List<Product> findAll() {
        logger.debug("Finding all products");
        return productRepository.findAll();
    }


    public Product findProductById(Long productId) {
        logger.debug("Finding product by id {}", productId);
        return productRepository.findById(productId).orElse(null);
    }

    public Product updateProduct(Long productId, Product updatedProductDetails) {
        logger.debug("Updating product with id {}", productId);
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