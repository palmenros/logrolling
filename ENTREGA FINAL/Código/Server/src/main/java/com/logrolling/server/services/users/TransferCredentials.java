package com.logrolling.server.services.users;

public class TransferCredentials {

    private String username;
    private String password;

    public TransferCredentials() {
    }

    public TransferCredentials(String username, String password) {
        this.username = username;
        this.password = password;
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
