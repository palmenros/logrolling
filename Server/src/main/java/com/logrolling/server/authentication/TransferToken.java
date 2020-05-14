package com.logrolling.server.authentication;

public class TransferToken {

    private String content;

    public TransferToken(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
