package com.logrolling.server.services.chats;

public class TransferMessage {

    private String from;
    private String to;
    private String content;

    public TransferMessage() {
    }

    public TransferMessage(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public TransferMessage(Message message) {
        this(message.getFrom(), message.getTo(), message.getContent());
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String title) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
