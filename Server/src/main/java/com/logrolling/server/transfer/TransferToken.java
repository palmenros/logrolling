package com.logrolling.server.transfer;

import com.logrolling.server.model.Token;

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
