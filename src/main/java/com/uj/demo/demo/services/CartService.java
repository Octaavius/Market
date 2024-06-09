package com.uj.demo.demo.services;

import com.uj.demo.demo.exceptions.UserNotExistsException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.*;

@Service
public class CartService {

    private final ProductService productService;

    private static final Logger logger = LogManager.getLogger(CartService.class);

    public CartService(ProductService productService) {
        this.productService = productService;
    }

    public String addItemToCart(String productName, String size, HttpSession session) throws UserNotExistsException {
        logger.debug("Checking if user is logged in");
        if (session.getAttribute("user") == null) {
            throw new UserNotExistsException();
        }
        logger.debug("Getting user's cart");
        List<Long> cart = (List<Long>) session.getAttribute("cart");
        if (cart == null) {
            logger.debug("Cart is empty. Creating new cart");
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        logger.debug("Adding product to cart");
        Long productId = productService.getId(productName, size);
        cart.add(productId);
        session.setAttribute("cart", cart);
        return "redirect:/profile";
    }

    public String removeItemFromCart(String productName, String size, HttpSession session) throws UserNotExistsException {
        logger.debug("Checking if user is logged in");
        if (session.getAttribute("user") == null) {
            throw new UserNotExistsException();
        }
        logger.debug("Getting user's cart");
        List<Long> cart = (List<Long>) session.getAttribute("cart");
        if (cart == null) {
            logger.warn("Cart is empty. Impossible to delete an item");
            return "profile";
        }
        Long productId = productService.getId(productName, size);
        logger.debug("Removing product from cart");
        boolean removed = cart.remove(productId);
        if (!removed) {
            logger.warn("No such item in the cart");
        }
        session.setAttribute("cart", cart);
        return "profile";
    }
}