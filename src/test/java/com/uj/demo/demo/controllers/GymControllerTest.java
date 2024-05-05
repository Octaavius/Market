package com.uj.demo.demo.controllers;

import com.uj.demo.demo.misc.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GymControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void addGym() throws Exception {
        String payload = "  {\n" +
                "    \"name\": \"AGH\",\n" +
                "    \"address\": \"KrK\",\n" +
                "    \"openingHours\": \"24\"\n" +
                "  }";
        mockMvc.perform(post("/gyms")
                        .contentType(APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk()).andExpect(content().string(
                        containsString("{}")
                ));
    }

    @Test
    public void testAddWithMock() {
        Calculator calculatorMock = mock(Calculator.class);
        when(calculatorMock.add(3, 7)).thenReturn(10);

        int result = calculatorMock.add(3, 7);

        assertEquals(10, result);
    }
    @Test
    public void testAddWithSpy() {
        Calculator calculator = new Calculator();
        Calculator calculatorSpy = spy(calculator);
        when(calculatorSpy.add(3, 7)).thenReturn(10);

        int result = calculatorSpy.add(3, 7);

        assertEquals(10, result);
        verify(calculatorSpy).add(3, 7);
    }
}