package com.logrolling.client.transfer;


public class Filter {

    private int minGrollies;
    private Coordinates coordinates;
    private double maxDistance; //Kilometers
    private int minDate; //Has to always be provided, seconds from 1970


    public Filter() {
        minGrollies = 0;
        coordinates = null;
        maxDistance = -1;
        minDate = 0;
    }

    public Filter(int minGrollies, Coordinates coordinates, double maxDistance, int minDate) {
        this.minGrollies = minGrollies;
        this.coordinates = coordinates;
        this.maxDistance = maxDistance;
        this.minDate = minDate;
    }

    public Filter(int minGrollies, double latitude, double longitude, double maxDistance) {
        this.minGrollies = minGrollies;
        this.coordinates = new Coordinates(latitude, longitude);
        this.maxDistance = maxDistance;
    }

    public Filter(double latitude, double longitude, double maxDistance) {
        this.minGrollies = 0;
        this.coordinates = new Coordinates(latitude, longitude);
        this.maxDistance = maxDistance;
    }

    public void setMaximumDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    public Double getMaxDistance() {
        return this.maxDistance;
    }

    public void setMinGrollies(int grollies) {
        minGrollies = grollies;
    }

    public Integer getMinGrollies() {
        return minGrollies;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Integer getMinDate() {
        return minDate;
    }

    public void setMinDate(int minDate) {
        this.minDate = minDate;
    }
}
