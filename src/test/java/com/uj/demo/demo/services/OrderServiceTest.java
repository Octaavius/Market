package com.uj.demo.demo.services;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.uj.demo.demo.models.*;
import com.uj.demo.demo.repositories.OrderRepository;
import com.uj.demo.demo.services.OrderService;
import com.uj.demo.demo.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductService productService;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveOrder() {
        Order order = new Order();
        when(orderRepository.save(order)).thenReturn(order);

        Order savedOrder = orderService.saveOrder(order);

        verify(orderRepository).save(order);
        assertEquals(order, savedOrder);
    }

    /*@Test
    public void testMakeOrderUserNotLoggedIn() {
        when(session.getAttribute("user")).thenReturn(null);

        String result = orderService.makeOrder(session, model, productService);

        assertEquals("redirect:/login", result);
    }*/

    @Test
    public void testMakeOrderCartWithUnavailableItems() {
        User user = new User();
        when(session.getAttribute("user")).thenReturn(user);

        List<Long> cart = Arrays.asList(1L, 2L);
        when(session.getAttribute("cart")).thenReturn(cart);

        Product product1 = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 0, 100.0);
        Product product2 = new Product(2L, ProductType.SHOES, "Adidas", "Superstar", "White", Sex.UNISEX, "9", 10, 80.0);

        when(productService.findProductById(1L)).thenReturn(product1);
        when(productService.findProductById(2L)).thenReturn(product2);

        String result = orderService.makeOrder(session, model, productService);

        verify(model).addAttribute("productsInCart", Arrays.asList(product2));
        assertEquals("redirect:/profile", result);
    }

    @Test
    public void testMakeOrderSuccess() {
        User user = new User();
        when(session.getAttribute("user")).thenReturn(user);

        List<Long> cart = Arrays.asList(1L, 2L);
        when(session.getAttribute("cart")).thenReturn(cart);

        Product product1 = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);
        Product product2 = new Product(2L, ProductType.SHOES, "Adidas", "Superstar", "White", Sex.UNISEX, "9", 10, 80.0);

        when(productService.findProductById(1L)).thenReturn(product1);
        when(productService.findProductById(2L)).thenReturn(product2);

        Order order = new Order(user);
        order.setProducts(Arrays.asList(product1, product2));

        String result = orderService.makeOrder(session, model, productService);

        verify(productService).updateProduct(product1.getId(), product1);
        verify(productService).updateProduct(product2.getId(), product2);
        verify(session).setAttribute("cart", new ArrayList<Long>());

        assertEquals("order", result);
    }
}
