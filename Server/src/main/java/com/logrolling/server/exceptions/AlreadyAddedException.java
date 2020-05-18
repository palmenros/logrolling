package com.logrolling.server.exceptions;

public class AlreadyAddedException extends RuntimeException {
    public AlreadyAddedException(String message) {
        super(message);
    }
}
