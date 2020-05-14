package com.logrolling.server.chats;

import com.logrolling.server.users.TransferUser;
import com.logrolling.server.users.User;

public class TransferMessagePreview {
    private TransferUser user;
    private TransferMessage message;

    public TransferMessagePreview(TransferUser u, TransferMessage m){
        user = u;
        message = m;
    }

    public TransferUser getUser(){
        return user;
    }

    public TransferMessage getMessage(){
        return message;
    }

}
