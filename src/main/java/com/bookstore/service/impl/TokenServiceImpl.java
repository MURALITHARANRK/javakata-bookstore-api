package com.bookstore.service.impl;

import com.bookstore.entities.User;
import com.bookstore.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
@Service
public class TokenServiceImpl implements TokenService{

    @Autowired
    private JWTUtil jwtUtil;
    @Override
    public String generateToken(User user) {
        return jwtUtil.generateToken(user.getUsername());
    }
}
