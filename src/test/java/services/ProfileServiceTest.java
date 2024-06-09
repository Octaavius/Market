package services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.models.ProductType;
import com.uj.demo.demo.models.Sex;
import com.uj.demo.demo.models.User;
import com.uj.demo.demo.services.ProductService;
import com.uj.demo.demo.services.ProfileService;
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

public class ProfileServiceTest {

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProfileService profileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /*@Test
    public void testShowProfileUserNotLoggedIn() {
        when(session.getAttribute("user")).thenReturn(null);

        String result = profileService.showProfile(model, session, productService);

        assertEquals("redirect:/login", result);
    }*/

    @Test
    public void testShowProfileWithEmptyCart() {
        User user = new User();
        user.setName("TestUser");

        when(session.getAttribute("user")).thenReturn(user);
        when(session.getAttribute("cart")).thenReturn(null);

        String result = profileService.showProfile(model, session, productService);

        verify(model).addAttribute("username", "TestUser");
        verify(model).addAttribute("cartIsEmpty", true);
        verify(model).addAttribute("productsInCart", new ArrayList<>());
        verify(model).addAttribute("sumPrice", 0.0);
        assertEquals("profile", result);
    }

    @Test
    public void testShowProfileWithNonEmptyCart() {
        User user = new User();
        user.setName("TestUser");

        List<Long> productsId = Arrays.asList(1L, 2L);
        Product product1 = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);
        Product product2 = new Product(2L, ProductType.SHOES, "Adidas", "Superstar", "White", Sex.UNISEX, "9", 10, 80.0);

        when(session.getAttribute("user")).thenReturn(user);
        when(session.getAttribute("cart")).thenReturn(productsId);
        when(productService.findProductById(1L)).thenReturn(product1);
        when(productService.findProductById(2L)).thenReturn(product2);

        String result = profileService.showProfile(model, session, productService);

        verify(model).addAttribute("username", "TestUser");
        verify(model).addAttribute("cartIsEmpty", false);
        verify(model).addAttribute("productsInCart", Arrays.asList(product1, product2));
        verify(model).addAttribute("sumPrice", 180.0);
        assertEquals("profile", result);
    }
}
