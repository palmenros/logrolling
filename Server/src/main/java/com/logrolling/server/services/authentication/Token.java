package com.logrolling.server.services.authentication;

public class Token {

    private int id;
    private String content;
    private String user;

    public Token(int id, String content, String user) {
        this.id = id;
        this.content = content;
        this.user = user;
    }

    public Token(String content, String user) {
        this.content = content;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
