package com.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
@Data
@AllArgsConstructor
@Getter
@Setter
public class BookDTO {

    @Column(name = "title")
    @NotBlank(message = "Title is mandatory")
    private String title;

    @Column(name = "author")
    @NotBlank(message = "Author name is mandatory")
    private String author;

    @Column(name = "price")
    @NotNull(message = "Price is mandatory")
    private Integer price;
}
