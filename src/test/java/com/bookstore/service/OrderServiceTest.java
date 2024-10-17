package com.bookstore.service;

import com.bookstore.dto.OrderDTO;
import com.bookstore.entities.Cart;
import com.bookstore.entities.Order;
import com.bookstore.entities.User;
import com.bookstore.repository.OrderRepository;
import com.bookstore.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserService userService;
    @Mock
    private CartService cartService;
    @InjectMocks
    private OrderService orderService;

    public OrderServiceTest() {
        this.orderService = new OrderServiceImpl();
    }

    @Test
    public void placeOrderTest() {
        String username = "mockUser";

        Order mockOrder = new Order();
        mockOrder.setId(1L);
        mockOrder.setTotalCost(500);
        mockOrder.setUsername(username);
        mockOrder.setOrderItems(new ArrayList<>());

        User mockUser = new User();
        mockUser.setUsername(username);

        Cart mockCart = new Cart();
        mockCart.setId(1L);
        mockCart.setUser(mockUser);

        Mockito.when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);
        Mockito.when(cartService.getCartDetailsByUser(mockUser)).thenReturn(Optional.of(mockCart));
        Mockito.when(userService.findUserbyName(username)).thenReturn(mockUser);

        OrderDTO orderDTO = orderService.placeOrder(username);

        assertNotNull(orderDTO);
        assertEquals("mockUser", orderDTO.getUsername());
        assertEquals(500, orderDTO.getTotalCost());

    }
}
