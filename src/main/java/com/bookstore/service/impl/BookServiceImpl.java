package com.bookstore.service.impl;

import com.bookstore.dto.BookDTO;
import com.bookstore.entities.Book;
import com.bookstore.exceptions.BookException;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = mapBookDTOtoBook(bookDTO);
        try {
            this.bookRepository.save(book);
            return bookDTO;
        } catch (Exception e) {
            throw new BookException("Failed to add book into database");
        }
    }

    @Override
    public Optional<Book> getBookById(Long bookId) {
        return this.bookRepository.findById(bookId);
    }

    private Book mapBookDTOtoBook(BookDTO bookDTO) {
        return this.modelMapper.map(bookDTO, Book.class);
    }
}
