package com.logrolling.server.model;

import java.util.ArrayList;
import java.util.List;

public class Chat {

    User user1;
    User user2;
    List<Message> messages;

    public Chat(User user1, User user2){
        this.user1 = user1;
        this.user2 = user2;
        messages = new ArrayList<Message>();
    }

    public void addMessage(Message message){
        messages.add(message);
    }

}
