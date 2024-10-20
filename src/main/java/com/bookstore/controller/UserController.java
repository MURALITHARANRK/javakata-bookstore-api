package com.bookstore.controller;

import com.bookstore.dto.UserDTO;
import com.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO addUser(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userDTO)).getBody();
    }

    @GetMapping("/login")
    public String tokenGeneration(@ModelAttribute("currentUsername") Object loginUsername) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.generateTokenForUser(loginUsername)).getBody();
    }

}
