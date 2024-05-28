package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.models.ProductType;
import com.uj.demo.demo.models.Sex;
import com.uj.demo.demo.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void addProduct() throws Exception {
        Product product1 = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);
        Product product2 = new Product(2L, ProductType.SHOES, "Adidas", "Superstar", "White", Sex.UNISEX, "9", 10, 80.0);

        when(productService.save(product1)).thenReturn(product1);
        when(productService.save(product2)).thenReturn(product2);

        String payload = "[{\"id\":1,\"type\":\"SHOES\",\"brand\":\"Nike\",\"model\":\"AirMax\",\"color\":\"Black\",\"sex\":\"UNISEX\",\"size\":\"10\",\"quantity\":5,\"price\":100.0}," +
                "{\"id\":2,\"type\":\"SHOES\",\"brand\":\"Adidas\",\"model\":\"Superstar\",\"color\":\"White\",\"sex\":\"UNISEX\",\"size\":\"9\",\"quantity\":10,\"price\":80.0}]";

        mockMvc.perform(post("/products")
                        .contentType(APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].brand", is("Nike")))
                .andExpect(jsonPath("$[0].model", is("AirMax")))
                .andExpect(jsonPath("$[0].color", is("Black")))
                .andExpect(jsonPath("$[0].sex", is("UNISEX")))
                .andExpect(jsonPath("$[0].size", is("10")))
                .andExpect(jsonPath("$[0].quantity", is(5)))
                .andExpect(jsonPath("$[0].price", is(100.0)))
                .andExpect(jsonPath("$[1].brand", is("Adidas")))
                .andExpect(jsonPath("$[1].model", is("Superstar")))
                .andExpect(jsonPath("$[1].color", is("White")))
                .andExpect(jsonPath("$[1].sex", is("UNISEX")))
                .andExpect(jsonPath("$[1].size", is("9")))
                .andExpect(jsonPath("$[1].quantity", is(10)))
                .andExpect(jsonPath("$[1].price", is(80.0)));
    }

    @Test
    void getAllDifferentProducts() throws Exception {
        Product product1 = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);
        Product product2 = new Product(2L, ProductType.SHOES, "Adidas", "Superstar", "White", Sex.UNISEX, "9", 10, 80.0);

        when(productService.getAll()).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].brand", is("Nike")))
                .andExpect(jsonPath("$[0].model", is("AirMax")))
                .andExpect(jsonPath("$[0].color", is("Black")))
                .andExpect(jsonPath("$[0].sex", is("UNISEX")))
                .andExpect(jsonPath("$[0].size", is("10")))
                .andExpect(jsonPath("$[0].quantity", is(5)))
                .andExpect(jsonPath("$[0].price", is(100.0)))
                .andExpect(jsonPath("$[1].brand", is("Adidas")))
                .andExpect(jsonPath("$[1].model", is("Superstar")))
                .andExpect(jsonPath("$[1].color", is("White")))
                .andExpect(jsonPath("$[1].sex", is("UNISEX")))
                .andExpect(jsonPath("$[1].size", is("9")))
                .andExpect(jsonPath("$[1].quantity", is(10)))
                .andExpect(jsonPath("$[1].price", is(80.0)));
    }

    @Test
    void getOneProduct() throws Exception {
        Product product1 = new Product(1L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "10", 5, 100.0);
        Product product2 = new Product(2L, ProductType.SHOES, "Nike", "AirMax", "Black", Sex.UNISEX, "11", 5, 100.0);

        when(productService.findByName("Nike AirMax")).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/products/Nike AirMax"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attributeExists("sizes"))
                .andExpect(model().attribute("product", is(product1)))
                .andExpect(model().attribute("sizes", containsInAnyOrder("10", "11")))
                .andExpect(view().name("product"));
    }
}
