package com.logrolling.server.controllers;

public class AuthenticableController {

    //TODO: Add proper documentation and exceptions

    //If authentication is not successful, throw an exception
    //If authentication is successful, return username of authenticated user
    protected String AuthenticateWithToken(String token) {
        return "";
    }

    //If authentication is not successful, throw an exception
    //Else generate a new token and return it
    protected String AuthenticateWithPassword(String user, String password) {
        return "";
    }

}
