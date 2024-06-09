package com.uj.demo.demo.services;

import com.uj.demo.demo.exceptions.UserNotExistsException;
import com.uj.demo.demo.models.Order;
import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.models.User;
import com.uj.demo.demo.repositories.OrderRepository;
import org.springframework.ui.Model;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private static final Logger logger = LogManager.getLogger(OrderService.class);

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public String makeOrder(HttpSession session, Model model, ProductService productService) {
        logger.debug("Checking if user exists");
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new UserNotExistsException();
        }
        logger.debug("Checking if cart exists");
        List<Long> cart = (List<Long>) session.getAttribute("cart");
        List<Product> productsInCart = new ArrayList<>();
        if (cart != null) {

            boolean hasNonAvaliableItems = false;
            for (Long id : cart) {
                Product product = productService.findProductById(id);
                if (product.getQuantity() == 0) {
                    hasNonAvaliableItems = true;
                    continue;
                }
                productsInCart.add(product);
            }
            if (hasNonAvaliableItems || productsInCart.size() == 0) {
                model.addAttribute("productsInCart", productsInCart);
                return "redirect:/profile";
            }
        }

        logger.debug("Adding items from cart to order");

        for (Product product : productsInCart) {
            product.decreaseQuantity(1);
            productService.updateProduct(product.getId(), product);
        }

        logger.debug("Saving order");

        Order order = new Order(user);
        order.setProducts(productsInCart);
        saveOrder(order);
        session.setAttribute("cart", new ArrayList<Long>());
        return "order";
    }
}