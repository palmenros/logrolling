package com.logrolling.server.model;

public class User {

    private int id;
    private String username;
    private String password;
    private int grollies;

    public User(int id, String username, String password, int grollies) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.grollies = grollies;
    }

    public User(String username, String password, int grollies) {
        this.username = username;
        this.password = password;
        this.grollies = grollies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGrollies(){ return grollies; }

    public void setGrollies(int grollies){ this.grollies = grollies; }
}
