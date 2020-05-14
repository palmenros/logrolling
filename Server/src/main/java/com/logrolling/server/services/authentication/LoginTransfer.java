package com.logrolling.server.services.authentication;

public class LoginTransfer {

    private String username;
    private String password;

    public LoginTransfer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginTransfer() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}