package com.bookstore.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @NotBlank(message = "Total Cost is mandatory")
    private Integer totalCost;

    @NotBlank(message = "Username is mandatory")
    private String username;

    private List<OrderItemDTO> orderItems;

}
