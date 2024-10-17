package com.bookstore.repository;

import com.bookstore.entities.Cart;
import com.bookstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
