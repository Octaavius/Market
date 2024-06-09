package com.uj.demo.demo.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.uj.demo.demo.services.CartService;
import com.uj.demo.demo.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;

import java.util.ArrayList;
import java.util.List;

public class CartServiceTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /*@Test
    public void testAddItemToCart() {
        String productName = "TestProduct";
        String size = "M";
        Long productId = 1L;

        MockHttpSession session = new MockHttpSession();
        List<Long> cart = new ArrayList<>();
        session.setAttribute("cart", cart);

        when(productService.getId(productName, size)).thenReturn(productId);

        String result = cartService.addItemToCart(productName, size, session);

        verify(productService).getId(productName, size);
        assertEquals("redirect:/profile", result);
        assertEquals(1, cart.size());
        assertEquals(productId, cart.get(0));
    }*/

    /*@Test
    public void testRemoveItemFromCart() {
        String productName = "TestProduct";
        String size = "M";
        Long productId = 1L;

        MockHttpSession session = new MockHttpSession();
        List<Long> cart = new ArrayList<>();
        cart.add(productId);
        session.setAttribute("cart", cart);

        when(productService.getId(productName, size)).thenReturn(productId);

        String result = cartService.removeItemFromCart(productName, size, session);

        verify(productService).getId(productName, size);
        assertEquals("profile", result);
        assertEquals(0, cart.size());
    }*/
}
