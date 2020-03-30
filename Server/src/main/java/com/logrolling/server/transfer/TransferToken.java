package com.logrolling.server.transfer;

import com.logrolling.server.model.Token;

public class TransferToken {

    private int id;
    private String content;
    private String user;

    public TransferToken(int id, String content, String user){
        this.id = id;
        this.content = content;
        this.user = user;
    }

    public TransferToken(Token token){
        this(token.getId(), token.getContent(), token.getUser());
    }

    public Integer getIde(){ return id; }

    public void setId(int id){ this.id = id; }

    public String getContent(){ return content; }

    public void setContent(String content){ this.content = content; }

    public String getUser(){return user; }

    public void setUser(String user){ this.user = user; }
}
