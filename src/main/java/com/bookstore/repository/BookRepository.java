package com.bookstore.repository;

import com.bookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
