package com.logrolling.server.model;

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

    public Integer getId(){ return id; }

    public void setId(int id){ this.id = id; }

    public String getContent(){ return content; }

    public void setContent(String content){ this.content = content; }

    public String getTitle(){ return title; }

    public void setTitle(String title){ this.title = title; }

    public Integer getPrice(){ return price; }

    public void setPrice(int price){ this.price = price; }
}
