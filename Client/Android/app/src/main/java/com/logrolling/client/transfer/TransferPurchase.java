package com.logrolling.client.transfer;

public class TransferPurchase {

    private String title;
    private String address;

    public TransferPurchase(String title, String address) {
        this.title = title;
        this.address = address;
    }

    public TransferPurchase() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}