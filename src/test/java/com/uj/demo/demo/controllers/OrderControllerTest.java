package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.*;
import com.uj.demo.demo.services.OrderService;
import com.uj.demo.demo.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private ProductService productService;

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
        User user = new User();
        session.setAttribute("user", user);
        session.setAttribute("cart", Arrays.asList());

        mockMvc.perform(get("/order").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile"));
    }
//
//    @Test
//    public void testOrderWithUnavailableItems() throws Exception {
//        MockHttpSession session = new MockHttpSession();
//        User user = new User();
//        session.setAttribute("user", user);
//        List<Long> cart = Arrays.asList(1L);
//        session.setAttribute("cart", cart);
//
//        Product product = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);
//        product.setQuantity(0);
//
//        when(productService.findProductById(1L)).thenReturn(product);
//
//        mockMvc.perform(get("/order").session(session))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/profile"));
//    }
//
//    @Test
//    public void testOrderWithAvailableItems() throws Exception {
//        MockHttpSession session = new MockHttpSession();
//        User user = new User();
//        session.setAttribute("user", user);
//        List<Long> cart = Arrays.asList(1L);
//        session.setAttribute("cart", cart);
//
//        Product product = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);
//        product.setId(1L);
//        product.setQuantity(5);
//
//        when(productService.findProductById(1L)).thenReturn(product);
//        when(productService.updateProduct(anyLong(), any(Product.class))).thenReturn(product);
//
//        mockMvc.perform(get("/order").session(session))
//                .andExpect(status().isOk())
//                .andExpect(view().name("order"));
//    }
}
