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
        //TODO: Implement
        return "1:hola";
    }

    public String getAuthenticatedUsername() {
        //TODO: Implement
        return "pedro";
    }
}
