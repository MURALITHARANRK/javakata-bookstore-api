package com.bookstore.controller;

import com.bookstore.dto.OrderDTO;
import com.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/order/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDTO placeOrder(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.placeOrder(username)).getBody();
    }

}
