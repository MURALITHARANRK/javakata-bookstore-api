package com.bookstore.service;

import com.bookstore.dto.BookDTO;
import com.bookstore.entities.Book;

import java.util.List;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
public interface BookService {

    public List<Book> getAllBooks();

    public BookDTO addBook(BookDTO bookDTO);

}
