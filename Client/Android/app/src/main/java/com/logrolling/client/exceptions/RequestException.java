package com.logrolling.client.exceptions;

public class RequestException extends Exception {

    public RequestException() {
        super();
    }

    public RequestException(String string) {
        super(string);
    }

    public RequestException(Throwable cause) {
        super(cause);
    }
}
