package com.board.backend.exception;

public class ValidationNullException extends RuntimeException {
    public ValidationNullException(String message) {
        super(message);
    }
}