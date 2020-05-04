package com.logrolling.server.model;

import com.logrolling.server.transfer.TransferMessage;

import java.util.Date;

public class Message {

    private int id;
    private String from;
    private String to;
    private String content;

    public Message(int id, String from, String to, String content){
        this.id = id;
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public Message(String from, String to, String content){
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public Message(TransferMessage m) {
        this.id = m.getId();
        this.from = m.getFrom();
        this.to = m.getTo();
        this.content = m.getContent();
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
