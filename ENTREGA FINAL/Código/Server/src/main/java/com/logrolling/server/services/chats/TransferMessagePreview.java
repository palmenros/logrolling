package com.logrolling.server.services.chats;

public class TransferMessagePreview {
    private String user;
    private String message;

    public TransferMessagePreview(String u, String m) {
        user = u;
        message = m;
    }

    public String getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

}
