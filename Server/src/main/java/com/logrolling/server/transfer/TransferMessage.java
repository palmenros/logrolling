package com.logrolling.server.transfer;


import com.logrolling.server.model.Message;

public class TransferMessage {

    private int id;
    private String from;
    private String to;
    private String content;

    public TransferMessage(int id, String from, String to, String content) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public TransferMessage(Message message) {
        this(message.getId(), message.getFrom(), message.getTo(), message.getContent());
    }

    public Integer getId(){ return id; }

    public void setId(int id){ this.id = id; }

    public String getContent(){ return content; }

    public void setContent(String content){ this.content = content; }

    public String getFrom(){ return from; }

    public void setFrom(String title){ this.from = from; }

    public String getTo(){ return to; }

    public void setTo(String to){ this.to = to; }
}
