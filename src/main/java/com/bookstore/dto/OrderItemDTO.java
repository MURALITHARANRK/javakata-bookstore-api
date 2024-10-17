package com.bookstore.dto;

import lombok.*;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private String bookId;
    private int quantity;
}
