package com.bookstore.service.impl;

import com.bookstore.entities.User;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
public interface TokenService {
    public String generateToken(User user);
}
