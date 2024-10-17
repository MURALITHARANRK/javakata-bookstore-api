package com.bookstore.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDTO {

    @NotBlank(message = "Username is mandatory")
    private String username;
    private List<CartItemDTO> cartItems;
}
