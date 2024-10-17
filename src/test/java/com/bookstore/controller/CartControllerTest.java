package com.bookstore.controller;

import com.bookstore.dto.CartDTO;
import com.bookstore.dto.CartItemDTO;
import com.bookstore.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addToCartTest() throws Exception {
        CartItemDTO cartItemDTO = new CartItemDTO("10", 10);
        String user = "user2";

        List<CartItemDTO> cartItemDTOList = new ArrayList<>();
        cartItemDTOList.add(cartItemDTO);

        CartDTO cartDTO = new CartDTO();
        cartDTO.setUsername(user);
        cartDTO.setCartItems(cartItemDTOList);

        Mockito.when(cartService.addOrRemoveItemsInCart(anyList(), anyString())).thenReturn(cartDTO);

        mockMvc.perform(post("/api/cart/{username}", user)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartItemDTOList)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // Expect JSON response
                .andExpect(jsonPath("$.username").value(user))  // Verify response content
                .andExpect(jsonPath("$.cartItems[0].bookId").value("10"))
                .andExpect(jsonPath("$.cartItems[0].quantity").value(10));

    }
}
