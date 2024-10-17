package com.bookstore.service;

import com.bookstore.dto.UserDTO;
import com.bookstore.entities.User;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
public interface UserService {
    public UserDTO addUser(UserDTO userDTO);
    public User findUserbyName(String usename);
    public boolean authenticate(String username, String password);
    public String generateTokenForUser(Object user);
}
