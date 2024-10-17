package com.bookstore.service;

import com.bookstore.dto.CartDTO;
import com.bookstore.dto.CartItemDTO;

import java.util.List;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
public interface CartService {
    public CartDTO addOrRemoveItemsInCart(List<CartItemDTO> cartItemDTOList, String username);

}
