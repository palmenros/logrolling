package com.logrolling.server.transfer;

import com.logrolling.server.model.Gift;

public class TransferGift {
    private int id;
    private String title;
    private String content;
    private int price;


    public TransferGift(int id, String title, String content, int price){
        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public TransferGift(String title, String content, int price){
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public TransferGift(Gift gift) {
        this(gift.getId(), gift.getTitle(), gift.getContent(), gift.getPrice());
    }

    public TransferGift(){}

    public Integer getId(){ return id; }

    public void setId(int id){ this.id = id; }

    public String getContent(){ return content; }

    public void setContent(String content){ this.content = content; }

    public String getTitle(){ return title; }

    public void setTitle(String title){ this.title = title; }

    public Integer getPrice(){ return price; }

    public void setPrice(int price){ this.price = price; }
}