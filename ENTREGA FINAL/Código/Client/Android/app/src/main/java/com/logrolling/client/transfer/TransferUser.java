package com.logrolling.client.transfer;

public class TransferUser {

    private int id;
    private String username;
    private int grollies;

    public TransferUser(int id, String username, int grollies) {
        this.id = id;
        this.username = username;
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

    public Integer getGrollies() {
        return grollies;
    }

    public void setGrollies(int grollies) {
        this.grollies = grollies;
    }
}
