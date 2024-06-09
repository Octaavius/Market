package com.uj.demo.demo.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.models.ProductType;
import com.uj.demo.demo.models.Sex;
import com.uj.demo.demo.models.User;
import com.uj.demo.demo.repositories.ProductRepository;
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
import java.util.Optional;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveNewProduct() {
        Product product = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);

        when(productRepository.findByModel("AirMax")).thenReturn(Arrays.asList());
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.save(product);

        verify(productRepository).save(product);
        assertNotNull(savedProduct);
        assertEquals(product, savedProduct);
    }

    @Test
    public void testSaveExistingProductWithSameSize() {
        Product product = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);
        Product existingProduct = new Product(2L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);

        when(productRepository.findByModel("AirMax")).thenReturn(Arrays.asList(existingProduct));

        Product savedProduct = productService.save(product);

        assertNull(savedProduct);
        verify(productRepository, never()).save(product);
    }

    @Test
    public void getExistId(){
        Product product = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);
        when(productRepository.findByModel("AirMax")).thenReturn(Arrays.asList(product));
        Long productId = productService.getId(product.getModel(), product.getSize());
        assertEquals(productId, product.getId());
    }

    @Test
    public void getUnExistId(){
        Product product = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);
        when(productRepository.findByModel("AirMax")).thenReturn(Arrays.asList(product));
        Long productId = productService.getId(product.getModel(), "1");
        assertEquals(productId, -1);
    }

    @Test
    public void testProductInfo() {
        Product product = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);

        when(productRepository.findByModel("AirMax")).thenReturn(Arrays.asList(product));
        when(session.getAttribute("user")).thenReturn(new User());

        String result = productService.productInfo("AirMax", model, session);

        verify(model).addAttribute("product", product);
        verify(model).addAttribute("sizes", Arrays.asList("10"));
        verify(model).addAttribute("loggedIn", true);
        assertEquals("product", result);
    }

    @Test
    public void testGetAllDifferentProducts() {
        Product product1 = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);
        Product product2 = new Product(2L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllDifferentProducts();

        assertEquals(1, products.size());
        assertEquals(product1, products.get(0));
    }

    @Test
    public void testGetAllSizes() {
        Product product1 = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);
        Product product2 = new Product(2L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "9", 10, 80.0);

        when(productRepository.findByModel("AirMax")).thenReturn(Arrays.asList(product1, product2));

        List<String> sizes = productService.getAllSizes("AirMax");

        assertEquals(2, sizes.size());
        assertTrue(sizes.contains("10"));
        assertTrue(sizes.contains("9"));
    }

    @Test
    public void testFindProductById() {
        Product product = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product foundProduct = productService.findProductById(1L);

        assertNotNull(foundProduct);
        assertEquals(product, foundProduct);
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);
        Product updatedProductDetails = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "11", 10, 120.0);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product updatedProduct = productService.updateProduct(1L, updatedProductDetails);

        verify(productRepository).save(product);
        assertEquals("11", updatedProduct.getSize());
        assertEquals(10, updatedProduct.getQuantity());
        assertEquals(120.0, updatedProduct.getPrice());
    }
}
