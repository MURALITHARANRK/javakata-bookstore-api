package com.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */

@Getter
@Setter
@AllArgsConstructor
@Data
public class CartItemDTO {

    private String bookId;
    private int quantity;
}
