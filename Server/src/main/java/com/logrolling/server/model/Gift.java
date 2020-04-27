package com.logrolling.server.model;

import com.logrolling.server.transfer.TransferGift;

public class Gift {

    private int id;
    private String title;
    private String content;
    private int price;

    public Gift(int id, String title, String content, int price){
        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public Gift(String title, String content, int price){
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public Gift(TransferGift g){
        this.title = g.getTitle();
        this.content = g.getContent();
        this.price = g.getPrice();
    }

    public Gift(){
    }

    public Integer getId(){ return id; }

    public void setId(int id){ this.id = id; }

    public String getContent(){ return content; }

    public void setContent(String content){ this.content = content; }

    public String getTitle(){ return title; }

    public void setTitle(String title){ this.title = title; }

    public Integer getPrice(){ return price; }

    public void setPrice(int price){ this.price = price; }
}
