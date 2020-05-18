package com.logrolling.server.exceptions;

public class NotEnoughGrolliesException extends RuntimeException {

    public NotEnoughGrolliesException() {
        super("No tienes suficientes grollies.");
    }

}
