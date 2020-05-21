package com.logrolling.server.services.chats;

public class Message {

    private int id;
    private String from;
    private String to;
    private String content;

    public Message(int id, String from, String to, String content) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public Message(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
