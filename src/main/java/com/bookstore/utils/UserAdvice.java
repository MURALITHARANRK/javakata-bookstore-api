package com.bookstore.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */

@ControllerAdvice
public class UserAdvice {
    @ModelAttribute("currentUsername")
    public Object getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication != null) ? authentication.getPrincipal() : null;
    }
}
