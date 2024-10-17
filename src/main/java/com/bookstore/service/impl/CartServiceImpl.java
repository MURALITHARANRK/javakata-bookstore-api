package com.bookstore.service.impl;

import com.bookstore.dto.CartDTO;
import com.bookstore.dto.CartItemDTO;
import com.bookstore.entities.Book;
import com.bookstore.entities.Cart;
import com.bookstore.entities.CartItem;
import com.bookstore.entities.User;
import com.bookstore.exceptions.CartException;
import com.bookstore.repository.CartRepository;
import com.bookstore.service.BookService;
import com.bookstore.service.CartService;
import com.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartDTO addOrRemoveItemsInCart(List<CartItemDTO> cartItemDTOList, String username) {

        User user = userService.findUserbyName(username);

        Cart cart = cartRepository.findByUser(user).map(existingCart -> {
            existingCart.getCartItems().clear();
            return existingCart;
        }).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });

        for(CartItemDTO cartItemDTO: cartItemDTOList){
            Optional<Book> book = bookService.getBookById(Long.valueOf(cartItemDTO.getBookId()));
            if(book.isPresent()){
                CartItem cartItem = addtoCartItem(book.get(), cartItemDTO.getQuantity(), cart);
                cart.addCartItem(cartItem);
            }else{
                throw new CartException("Book details not found to add in cart");
            }
        }

        try{
            cartRepository.save(cart);
        }catch (Exception e){
            throw new CartException("Unable to save the cart details");
        }

        return mapCarttoCartDTO(cartItemDTOList, cart);
    }

    private CartDTO mapCarttoCartDTO(List<CartItemDTO> cartItemDTOList, Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUsername(cart.getUser().getUsername());
        cartDTO.setCartItems(cartItemDTOList);
        return cartDTO;
    }

    private CartItem addtoCartItem(Book book, int quantity, Cart cart) {
        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart);
        return cartItem;
    }

}
