package com.uj.demo.demo.controllers;

import com.uj.demo.demo.services.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.servlet.http.HttpSession;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Captor
    private ArgumentCaptor<HttpSession> sessionCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddToCart() throws Exception {
        String productName = "TestProduct";
        String size = "M";

        MockHttpSession session = new MockHttpSession();

        mockMvc.perform(post("/add-to-cart")
                        .param("productName", productName)
                        .param("size", size)
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile"));

        verify(cartService, times(1)).addItemToCart(eq(productName), eq(size), sessionCaptor.capture());

        HttpSession capturedSession = sessionCaptor.getValue();
        assert session == capturedSession;
    }

    @Test
    public void testRemoveFromCart() throws Exception {
        String productName = "TestProduct";
        String size = "M";

        MockHttpSession session = new MockHttpSession();

        mockMvc.perform(post("/remove-from-cart")
                        .param("productName", productName)
                        .param("size", size)
                        .session(session))
                .andExpect(status().isOk());

        verify(cartService, times(1)).removeItemFromCart(eq(productName), eq(size), sessionCaptor.capture());

        HttpSession capturedSession = sessionCaptor.getValue();
        assert session == capturedSession;
    }
}
