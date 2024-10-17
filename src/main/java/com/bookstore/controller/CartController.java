package com.bookstore.controller;

import com.bookstore.dto.CartDTO;
import com.bookstore.dto.CartItemDTO;
import com.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart/{username}")
    public CartDTO addOrRemoveItemsInCart(@RequestBody List<CartItemDTO> cartItemDTOList, @PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.addOrRemoveItemsInCart(cartItemDTOList,username)).getBody();
    }

}
