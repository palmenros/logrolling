package com.logrolling.server.transfer;

import com.logrolling.server.model.User;

public class TransferUser {

    //TODO: Complete transfer class with all attributes

    private int id;
    private String username;

    public TransferUser(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public TransferUser(User user) {
        this(user.getId(), user.getUsername());
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
}
