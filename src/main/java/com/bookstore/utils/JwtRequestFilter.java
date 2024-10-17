package com.bookstore.utils;

import com.bookstore.entities.User;
import com.bookstore.exceptions.AuthException;
import com.bookstore.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private  JWTUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwtToken);
                validateJWTToken(username, jwtToken, request);
            } catch (IllegalArgumentException e) {
                throw new AuthException("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                throw new AuthException("User Token has expired");
            }
        } else if (request.getRequestURI().equals("/api/login") && requestTokenHeader.startsWith("Basic ")) {

            String base64Credentials = requestTokenHeader.substring("Basic ".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));

            final String[] values = credentials.split(":", 2);
            username = values[0];
            String password = values[1];

            User userDetail = userService.findUserbyName(username);
            if (userDetail == null || !userService.authenticate(username, password)) {
                throw new AuthException("Invalid credentials");
            }
            setUserInAuthContext(request, userDetail);

        }

        filterChain.doFilter(request, response);
    }

    private void validateJWTToken(String username, String jwt, HttpServletRequest request) {
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User userDetail = userService.findUserbyName(username);
            if(userDetail == null){
                throw new RuntimeException("User is not found : " + username);
            }
            if (jwtUtil.validateToken(jwt, userDetail.getUsername())) {
                setUserInAuthContext(request, userDetail);
            }
        }
    }

    private void setUserInAuthContext(HttpServletRequest request, User userDetail) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetail, null, null);
        usernamePasswordAuthenticationToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

}