package com.logrolling.server.services.authentication;

public class TransferToken {

    private String content;

    public TransferToken(String username, String password) {
        this.content = AuthenticationService.authenticateWithPassword(username, password);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
