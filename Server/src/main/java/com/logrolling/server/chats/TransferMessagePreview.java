package com.logrolling.server.chats;

import com.logrolling.server.users.TransferUser;
import com.logrolling.server.users.User;

public class TransferMessagePreview {
    private String user;
    private String message;

    public TransferMessagePreview(String u, String m){
        user = u;
        message = m;
    }

    public String getUser(){
        return user;
    }

    public String getMessage(){
        return message;
    }

}
