package com.bookstore.exceptions;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
public class BookException extends RuntimeException{
    public BookException(String errorMessage) {
        super(errorMessage);
    }
}
