package com.logrolling.server.model;

public class ErrorMessage {

    String message;
    int errorCode;
    String documentation;

    public ErrorMessage(String message, int errorCode, String documentation){
        this.message = message;
        this.errorCode = errorCode;
        this.documentation = documentation;
    }

}
