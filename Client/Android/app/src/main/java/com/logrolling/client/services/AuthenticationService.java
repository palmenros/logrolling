package com.logrolling.client.services;

public class AuthenticationService {

    private static  AuthenticationService instance;

    private  AuthenticationService() {
    }

    public static AuthenticationService getInstance() {
        if(instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    public String getAuthToken() {
        return "1:hola";
    }

}
