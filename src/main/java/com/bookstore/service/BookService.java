package com.bookstore.service;

import com.bookstore.dto.BookDTO;
import com.bookstore.entities.Book;

import java.util.List;
import java.util.Optional;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
public interface BookService {

    public List<Book> getAllBooks();

    public BookDTO addBook(BookDTO bookDTO);

    public Optional<Book> getBookById(Long bookId);

}
