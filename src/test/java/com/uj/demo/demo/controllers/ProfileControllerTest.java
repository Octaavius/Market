package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.models.ProductType;
import com.uj.demo.demo.models.Sex;
import com.uj.demo.demo.models.User;
import com.uj.demo.demo.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProfileControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProfileController profileController;

    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        session = new MockHttpSession();
    }

    @Test
    public void testProfile_UserNotLoggedIn_RedirectToLogin() {
        String expectedView = "redirect:/login";
        String actualView = profileController.profile(model, session);
        assertEquals(expectedView, actualView);
    }

    @Test
    public void testProfile_UserLoggedInWithEmptyCart_ReturnProfilePage() {
        String expectedView = "profile";
        User user = new User();
        user.setName("John");
        session.setAttribute("user", user);
        List<Long> productsId = null;
        session.setAttribute("cart", productsId);
        String actualView = profileController.profile(model, session);
        assertEquals(expectedView, actualView);
    }


    @Test
    public void testProfile_UserLoggedInWithProductsInCart_ReturnProfilePageWithProducts() {
        String expectedView = "profile";
        User user = new User();
        user.setName("John");
        session.setAttribute("user", user);
        List<Long> productsId = new ArrayList<>();
        productsId.add(1L);
        productsId.add(2L);
        session.setAttribute("cart", productsId);
        List<Product> products = new ArrayList<>();
        Product product1 = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);
        Product product2 = new Product(2L, ProductType.SHOES, "Adidas", "Superstar", "White", Sex.UNISEX, "9", 10, 80.0);

        products.add(product1);
        products.add(product2);
        when(productService.findProductById(1L)).thenReturn(product1);
        when(productService.findProductById(2L)).thenReturn(product2);

        String actualView = profileController.profile(model, session);
        assertEquals(expectedView, actualView);
        verify(model).addAttribute("username", "John");
        verify(model).addAttribute("cartIsEmpty", false);
        verify(model).addAttribute("productsInCart", products);
        verify(model).addAttribute("sumPrice", 180.0);
    }
}
