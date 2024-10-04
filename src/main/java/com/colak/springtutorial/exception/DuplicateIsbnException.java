package com.colak.springtutorial.exception;

public class DuplicateIsbnException extends Exception {

    public DuplicateIsbnException(String message) {
        super(message);
    }
}
