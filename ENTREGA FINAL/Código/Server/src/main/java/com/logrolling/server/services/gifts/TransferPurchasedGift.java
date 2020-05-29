package com.logrolling.server.services.gifts;

public class TransferPurchasedGift {

    private int id;
    private int giftId;
    private String address;
    private String username;

    public TransferPurchasedGift(int id, int giftId, String address, String username) {
        this.id = id;
        this.giftId = giftId;
        this.address = address;
        this.username = username;
    }

    public TransferPurchasedGift(int giftId, String address, String username) {
        this.giftId = giftId;
        this.address = address;
        this.username = username;
    }

    public TransferPurchasedGift(PurchasedGift g) {
        this.id = g.getId();
        this.giftId = g.getGiftId();
        this.address = g.getAddress();
        this.username = g.getUsername();
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(int giftId) {
        this.giftId = giftId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
