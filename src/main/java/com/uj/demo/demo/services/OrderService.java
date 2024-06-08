package com.uj.demo.demo.services;

import com.uj.demo.demo.models.Order;
import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.models.User;
import com.uj.demo.demo.repositories.OrderRepository;
import org.springframework.ui.Model;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public String makeOrder(HttpSession session, Model model, ProductService productService){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Long> cart = (List<Long>) session.getAttribute("cart");
        List<Product> productsInCart = new ArrayList<>();
        if (cart != null) {
            boolean hasNonAvaliableItems = false;
            for (Long id : cart) {
                Product product = productService.findProductById(id);
                if(product.getQuantity() == 0){
                    hasNonAvaliableItems = true;
                    continue;
                }
                productsInCart.add(product);
            }
            if(hasNonAvaliableItems || productsInCart.size() == 0){
                model.addAttribute("productsInCart", productsInCart);
                return "redirect:/profile";
            }
        }

        for (Product product : productsInCart){
            product.decreaseQuantity(1);
            productService.updateProduct(product.getId(), product);
        }

        Order order = new Order(user);
        order.setProducts(productsInCart);
        saveOrder(order);
        session.setAttribute("cart", new ArrayList<Long>());
        return "order";
    }
}