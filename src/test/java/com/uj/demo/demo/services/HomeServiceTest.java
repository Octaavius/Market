package com.uj.demo.demo.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.models.ProductType;
import com.uj.demo.demo.models.Sex;
import com.uj.demo.demo.models.User;
import com.uj.demo.demo.services.HomeService;
import com.uj.demo.demo.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

public class HomeServiceTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @InjectMocks
    private HomeService homeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIndexLoggedIn() {
        User user = new User();
        user.setName("TestUser");

        when(session.getAttribute("user")).thenReturn(user);

        Product product1 = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);
        Product product2 = new Product(2L, ProductType.SHOES, "Adidas", "Superstar", "White", Sex.UNISEX, "9", 0, 80.0);
        List<Product> products = Arrays.asList(product1, product2);

        when(productService.findAll()).thenReturn(products);

        String result = homeService.index(model, session, productService);

        verify(model).addAttribute("loggedIn", true);
        verify(model).addAttribute("products", Arrays.asList(product1));
        assertEquals("index", result);
    }
}
