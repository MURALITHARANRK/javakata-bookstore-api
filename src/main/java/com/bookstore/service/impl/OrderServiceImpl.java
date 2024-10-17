package com.bookstore.service.impl;

import com.bookstore.dto.OrderDTO;
import com.bookstore.dto.OrderItemDTO;
import com.bookstore.entities.*;
import com.bookstore.exceptions.BookException;
import com.bookstore.exceptions.OrderException;
import com.bookstore.repository.OrderRepository;
import com.bookstore.service.BookService;
import com.bookstore.service.CartService;
import com.bookstore.service.OrderService;
import com.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Override
    public OrderDTO placeOrder(String username) {
        Cart cart = getCartByUsername(username);
        List<OrderItem> orderItems = getOrderItemsFromCart(cart);
        Integer totalItemsCost = calculateTotalItemsCost(orderItems);
        Order order = placeCartOrder(username, orderItems, totalItemsCost);
        deleteCartThePlacedOrder(cart);
        return mapOrderToOrderDTO(order);
    }
    private void deleteCartThePlacedOrder(Cart cart) {
        this.cartService.removeCart(cart);
    }

    private Order placeCartOrder(String username, List<OrderItem> orderItems, Integer totalItemsCost) {
        Order cartOrder = new Order();
        cartOrder.setUsername(username);
        cartOrder.setOrderItems(orderItems);
        cartOrder.setTotalCost(totalItemsCost);
        return orderRepository.save(cartOrder);
    }

    private Integer calculateTotalItemsCost(List<OrderItem> orderItems) {
        return (int) orderItems.stream()
                .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
                .sum();
    }

    private List<OrderItem> getOrderItemsFromCart(Cart cart) {
        return cart.getCartItems().stream()
                .map(cartItem -> {
                    Book book = bookService.getBookById(cartItem.getBook().getId())
                            .orElseThrow(() -> new BookException("Book is not available"));
                    OrderItem orderItem = new OrderItem();
                    orderItem.setBook(book);
                    orderItem.setQuantity(cartItem.getQuantity());
                    return orderItem;
                })
                .collect(Collectors.toList());
    }

    private Cart getCartByUsername(String username) {
        User user = this.userService.findUserbyName(username);
        Optional<Cart> userCart = this.cartService.getCartDetailsByUser(user);
        if(userCart.isEmpty()){
            throw new OrderException("User does not have any items in the cart");
        }
        return userCart.get();
    }

    private OrderDTO mapOrderToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
        orderDTO.setUsername(order.getUsername());
        for(OrderItem orderItem: order.getOrderItems()){
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setQuantity(orderItem.getQuantity());
            orderItemDTO.setBookId(orderItem.getBook().getId().toString());
            orderItemDTOList.add(orderItemDTO);
        }
        orderDTO.setOrderItems(orderItemDTOList);
        orderDTO.setTotalCost(order.getTotalCost());
        return orderDTO;
    }
}
