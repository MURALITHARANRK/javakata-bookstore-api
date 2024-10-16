package com.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiErrorDTO {
    private String status;
    private String errorMessage;
    private HttpStatus httpStatus;
}
