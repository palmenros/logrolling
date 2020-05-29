package com.logrolling.client.transfer;

public class TransferPaymentToken {

    private String content;

    public TransferPaymentToken(String content) {
        this.content = content;
    }

    public TransferPaymentToken() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
