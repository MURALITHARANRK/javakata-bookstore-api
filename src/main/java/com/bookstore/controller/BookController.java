package com.bookstore.controller;

import com.bookstore.dto.BookDTO;
import com.bookstore.entities.Book;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDTO addBook(@RequestBody @Valid BookDTO bookDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(bookDTO)).getBody();
    }
}
