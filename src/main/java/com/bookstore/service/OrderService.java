package com.bookstore.service;

import com.bookstore.dto.OrderDTO;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
public interface OrderService {

    public OrderDTO placeOrder(String username);
}
