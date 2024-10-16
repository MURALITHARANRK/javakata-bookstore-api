package com.bookstore.controller;

import com.bookstore.entities.Book;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks()).getBody();
    }
}
