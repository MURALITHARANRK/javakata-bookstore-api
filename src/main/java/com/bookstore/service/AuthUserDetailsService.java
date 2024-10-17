package com.bookstore.service;

import com.bookstore.entities.User;
import com.bookstore.exceptions.AuthException;
import com.bookstore.utils.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
@Service
public class AuthUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserbyName(username);
        if (user != null) {
            return CustomUserDetails.build(username, user.getPassword());
        }
        throw new AuthException("User not found :"+ username);

    }
}
