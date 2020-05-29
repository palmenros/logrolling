package com.logrolling.client.transfer;

import java.util.List;

public class TransferChat {

    private String user1;
    private String user2;
    private List<TransferMessage> messages;

    public TransferChat(String user1, String user2, List<TransferMessage> messages) {
        this.user1 = user1;
        this.user2 = user2;
        this.messages = messages;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public List<TransferMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<TransferMessage> messages) {
        this.messages = messages;
    }

}
