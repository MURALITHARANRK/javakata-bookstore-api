package com.bookstore.service;

import com.bookstore.dto.CartDTO;
import com.bookstore.dto.CartItemDTO;
import com.bookstore.entities.Cart;
import com.bookstore.entities.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
public interface CartService {
    public CartDTO addOrRemoveItemsInCart(List<CartItemDTO> cartItemDTOList, String username);

    public void removeCart(Cart cart);

    public Optional<Cart> getCartDetailsByUser(User user);

}
