package com.bookstore.service;

import com.bookstore.dto.BookDTO;
import com.bookstore.entities.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
@ExtendWith(MockitoExtension.class)

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookService bookService;
    @Spy
    private ModelMapper modelMapper;

    private BookServiceTest() {
        this.bookService = new BookServiceImpl();
    }

    @Test
    public void getAllBooksTest() {

        Book mockBook1 = new Book(1L, "Clean Code", "Uncle Bob", 200);
        Book mockBook2 = new Book(2L, "Deep Work", "Cal Newport", 150);
        List<Book> mockBookList = Arrays.asList(mockBook1, mockBook2);

        Mockito.when(bookRepository.findAll()).thenReturn(mockBookList);

        List<Book> bookList = bookService.getAllBooks();

        assertEquals(2, bookList.size());

    }

    @Test
    public void addBookTest() {

        Book mockBook = new Book(1L,"Clean Code", "Uncle Bob", 200);
        BookDTO mockBookDTO = new BookDTO("Clean Code", "Uncle Bob", 200);

        Mockito.when(bookRepository.save(any(Book.class))).thenReturn(mockBook);

        BookDTO bookDTO = bookService.addBook(mockBookDTO);

        assertEquals(mockBook.getAuthor(), bookDTO.getAuthor());

    }
}
