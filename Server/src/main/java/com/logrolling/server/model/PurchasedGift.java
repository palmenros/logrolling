package com.logrolling.server.model;

import com.logrolling.server.transfer.TransferGift;

public class PurchasedGift {

    private int id;
    private int giftId;
    private String address;
    private String username;
    private boolean sent;

    public PurchasedGift(int id, int giftId, String address, String username){
        this.id = id;
        this.giftId = giftId;
        this.address = address;
        this.username = username;
        this.sent = false;
    }

    public PurchasedGift(int giftId, String address, String username){

        this.giftId = giftId;
        this.address = address;
        this.username = username;
        this.sent = false;
    }


    public Integer getId(){ return id; }

    public void setId(int id){ this.id = id; }

    public Integer getGiftId(){ return giftId; }

    public void setGiftId(int giftId){ this.giftId = giftId; }

    public String getAddress(){ return address; }

    public void setAddress(String address){ this.address = address; }

    public String getUsername(){ return username; }

    public void setUsername(String username){ this.username = username; }

    public boolean getSent(){ return sent; }

    public void setSent(boolean sent){ this.sent = sent; }


}
