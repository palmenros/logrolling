package com.logrolling.server.exceptions;

public class AlreadyExistingUsername extends RuntimeException {

    public AlreadyExistingUsername() {
        super("Ese nombre de usuario no está disponible.");
    }

}
