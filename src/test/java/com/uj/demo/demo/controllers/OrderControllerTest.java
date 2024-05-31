package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.*;
import com.uj.demo.demo.services.OrderService;
import com.uj.demo.demo.services.ProductService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private OrderService orderService;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();
    }

    @Test
    public void testOrderWithoutUser() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(get("/order").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void testOrderWithEmptyCart() throws Exception {
        MockHttpSession session = new MockHttpSession();
        User user = new User(1L, "user", "user", "user");
        session.setAttribute("user", user);
        session.setAttribute("cart", new ArrayList<>());

        mockMvc.perform(get("/order").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile"));
    }

    @Test
    public void testOrderWithUnavailableItems() throws Exception {
        MockHttpSession session = new MockHttpSession();
        User user = new User(1L, "user", "user", "user");
        session.setAttribute("user", user);
        List<Long> cart = Collections.singletonList(1L);
        session.setAttribute("cart", cart);

        Product product = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);
        product.setQuantity(0);

        when(productService.findProductById(1L)).thenReturn(product);

        mockMvc.perform(get("/order").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile"));
    }

    @Test
    public void testOrderWithAvailableItems() throws Exception {
        MockHttpSession session = new MockHttpSession();
        User user = new User(1L, "user", "user", "user");
        session.setAttribute("user", user);
        List<Long> cart = Collections.singletonList(1L);
        session.setAttribute("cart", cart);

        Product product = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);
        product.setId(1L);
        product.setQuantity(5);

        when(productService.findProductById(1L)).thenReturn(product);
        when(productService.updateProduct(anyLong(), any(Product.class))).thenReturn(product);

        mockMvc.perform(get("/order").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("order"));
    }
}
