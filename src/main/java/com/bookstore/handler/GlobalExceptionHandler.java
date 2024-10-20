package com.bookstore.handler;

import com.bookstore.dto.ApiErrorDTO;
import com.bookstore.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookException.class)
    public ResponseEntity<ApiErrorDTO> handleBookException(BookException bookException){
        ApiErrorDTO apiErrorDTO = ApiErrorDTO.builder().status("FAILED").errorMessage(bookException.getMessage()).httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(apiErrorDTO, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiErrorDTO> handleUserException(UserException userException){
        ApiErrorDTO apiErrorDTO = ApiErrorDTO.builder().status("FAILED").errorMessage(userException.getMessage()).httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(apiErrorDTO, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(CartException.class)
    public ResponseEntity<ApiErrorDTO> handleCartException(CartException cartException){
        ApiErrorDTO apiErrorDTO = ApiErrorDTO.builder().status("FAILED").errorMessage(cartException.getMessage()).httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(apiErrorDTO, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ApiErrorDTO> handleCartException(OrderException orderException){
        ApiErrorDTO apiErrorDTO = ApiErrorDTO.builder().status("FAILED").errorMessage(orderException.getMessage()).httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(apiErrorDTO, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ApiErrorDTO> handleCartException(AuthException authException){
        ApiErrorDTO apiErrorDTO = ApiErrorDTO.builder().status("FAILED").errorMessage(authException.getMessage()).httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(apiErrorDTO, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errorMap, HttpStatus.UNPROCESSABLE_ENTITY);

    }
}
