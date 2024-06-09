package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testAddProduct() throws Exception {
        List<Product> products = Arrays.asList(new Product(1L, null, "Brand1", "Model1", "Color1", null, "Size1", 1, 100.0));
        when(productService.addProducts(products)).thenReturn(products);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"id\":1,\"brand\":\"Brand1\",\"model\":\"Model1\",\"color\":\"Color1\",\"size\":\"Size1\",\"quantity\":1,\"price\":100.0}]"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"brand\":\"Brand1\",\"model\":\"Model1\",\"color\":\"Color1\",\"size\":\"Size1\",\"quantity\":1,\"price\":100.0}]"));

        verify(productService, times(1)).addProducts(products);
    }

    @Test
    public void testGetProductsByName() throws Exception {
        List<Product> products = Arrays.asList(new Product(1L, null, "Brand1", "Model1", "Color1", null, "Size1", 1, 100.0));
        when(productService.findByName("Model1")).thenReturn(products);

        mockMvc.perform(get("/products/name")
                        .param("name", "Model1"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"brand\":\"Brand1\",\"model\":\"Model1\",\"color\":\"Color1\",\"size\":\"Size1\",\"quantity\":1,\"price\":100.0}]"));

        verify(productService, times(1)).findByName("Model1");
    }

    @Test
    public void testGetAllSizes() throws Exception {
        List<String> sizes = Arrays.asList("Size1", "Size2");
        when(productService.getAllSizes("Model1")).thenReturn(sizes);

        mockMvc.perform(get("/products/sizes")
                        .param("name", "Model1"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"Size1\",\"Size2\"]"));

        verify(productService, times(1)).getAllSizes("Model1");
    }

    @Test
    public void testGetAllDifferentProducts() throws Exception {
        List<Product> products = Arrays.asList(new Product(1L, null, "Brand1", "Model1", "Color1", null, "Size1", 1, 100.0));
        when(productService.getAllDifferentProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"brand\":\"Brand1\",\"model\":\"Model1\",\"color\":\"Color1\",\"size\":\"Size1\",\"quantity\":1,\"price\":100.0}]"));

        verify(productService, times(1)).getAllDifferentProducts();
    }
}
