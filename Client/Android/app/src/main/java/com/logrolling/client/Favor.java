package com.logrolling.client;

public class Favor {
    private String name, description, adress, favor, maxTime, distance;
    private int photo, price;

    public Favor(){

    }

    public Favor(String name, String description, String adress, String favor, int photo, int price, String maxTime, String distance) {
        this.name = name;
        this.description = description;
        this.adress = adress;
        this.favor = favor;
        this.photo = photo;
        this.price = price;
        this.maxTime = maxTime;
        this.distance = distance;
    }

    public String getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(String maxTime) {
        this.maxTime = maxTime;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getFavor() {
        return favor;
    }

    public void setFavor(String favor) {
        this.favor = favor;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
