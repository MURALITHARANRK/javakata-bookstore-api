package com.bookstore.service;

import com.bookstore.dto.CartDTO;
import com.bookstore.dto.CartItemDTO;
import com.bookstore.entities.Book;
import com.bookstore.entities.Cart;
import com.bookstore.entities.CartItem;
import com.bookstore.entities.User;
import com.bookstore.repository.CartRepository;
import com.bookstore.service.impl.BookServiceImpl;
import com.bookstore.service.impl.CartServiceImpl;
import com.bookstore.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private UserService userService;

    @Mock
    private  BookService bookService;

    @InjectMocks
    private CartService cartService;

    public CartServiceTest() {
        this.cartService = new CartServiceImpl();
    }

    @Test
    public void addOrRemoveItemsInCartTest(){

        String user = "mockUser";

        CartItemDTO mockCartItemDTO = new CartItemDTO("1", 10);
        List<CartItemDTO> mockCartItemDTOList = new ArrayList<>();
        mockCartItemDTOList.add(mockCartItemDTO);

        User mockUser = new User();
        mockUser.setUsername(user);

        Book mockBook = new Book();
        mockBook.setId(1L);
        mockBook.setTitle("MockBookTitle");
        mockBook.setAuthor("MockAuthor");
        mockBook.setPrice(100);

        Cart mockCart = new Cart();
        mockCart.setUser(mockUser);

        Mockito.when(userService.findUserbyName(anyString())).thenReturn(mockUser);
        Mockito.when(cartRepository.findByUser(mockUser)).thenReturn(Optional.of(mockCart));
        Mockito.when(bookService.getBookById(anyLong())).thenReturn(Optional.of(mockBook));
        Mockito.when(cartRepository.save(any(Cart.class))).thenReturn(mockCart);

        CartDTO cartDTO = cartService.addOrRemoveItemsInCart(mockCartItemDTOList, user);

        assertNotNull(cartDTO);

    }
}

