package com.book.read.tracker.exception;

public class BookExistException extends RuntimeException {
    public BookExistException(String message) {
        super(message);
    }
}
