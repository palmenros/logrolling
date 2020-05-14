package com.logrolling.client.transfer;

public class Persona {
    private String name;
    private String last_message;
    private int photo;

    public Persona(String name, String last_message, int photo) {
        this.name = name;
        this.last_message = last_message;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_message() {
        return last_message;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
