package com.bookstore.controller;

import com.bookstore.entities.Book;
import com.bookstore.service.BookService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;

    @Test
    public void getAllBooksTest() throws Exception {

        Book mockBook1 = new Book(1L, "Clean Code", "Uncle Bob", 200);
        Book mockBook2 = new Book(2L, "Deep Work", "Cal Newport", 150);
        List<Book> mockBookList = Arrays.asList(mockBook1, mockBook2);

        Mockito.when(bookService.getAllBooks()).thenReturn(mockBookList);

        ResultActions response = mockMvc.perform(get("/api/book").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", CoreMatchers.is("Clean Code")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author", CoreMatchers.is("Uncle Bob")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price", CoreMatchers.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title", CoreMatchers.is("Deep Work")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].author", CoreMatchers.is("Cal Newport")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price", CoreMatchers.is(150)));


    }

}
