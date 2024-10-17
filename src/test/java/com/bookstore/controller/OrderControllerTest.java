package com.bookstore.controller;

import com.bookstore.dto.OrderDTO;
import com.bookstore.dto.OrderItemDTO;
import com.bookstore.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void placeOrderTest() throws Exception {
        String mockUser = "user2";
        OrderDTO mockOrderDTO = new OrderDTO();
        List<OrderItemDTO> mockOrderItemDTOList = Arrays.asList(
                new OrderItemDTO("10", 10),
                new OrderItemDTO("5", 20)
        );
        mockOrderDTO.setUsername(mockUser);
        mockOrderDTO.setOrderItems(mockOrderItemDTOList);

        Mockito.when(orderService.placeOrder(anyString())).thenReturn(mockOrderDTO);

        mockMvc.perform(post("/api/order/{username}", mockUser)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // Expect JSON response
                .andExpect(jsonPath("$.username").value(mockUser))  // Verify response content
                .andExpect(jsonPath("$.orderItems[0].bookId").value("10"))
                .andExpect(jsonPath("$.orderItems[0].quantity").value(10))
                .andExpect(jsonPath("$.orderItems[1].bookId").value("5"))
                .andExpect(jsonPath("$.orderItems[1].quantity").value(20));

    }

}
