package com.logrolling.server.services.payment;

public class TransferTransaction {

    private String nonce;
    private int amount;         //Amount in cents

    public TransferTransaction(String nonce, int amount) {
        this.nonce = nonce;
        this.amount = amount;
    }

    public TransferTransaction() {
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
