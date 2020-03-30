package com.logrolling.server.model;

import java.util.Date;

public class Filter {

    private int minGrollies;
    private Coordinates coordinates;
    private double maxDistance;//EN KM

    private boolean awarded;//Implement
    private Date minDate;//Implement


    public Filter(){

        minGrollies = 0;
        coordinates = null;
        maxDistance = -1;

    }
    public Filter(int minGrollies, double latitude, double longitude, double maxDistance){
        this.minGrollies = minGrollies;
        this.coordinates = new Coordinates(latitude, longitude);
        this.maxDistance = maxDistance;

    }

    public Filter(double latitude, double longitude, double maxDistance){
        this.minGrollies = 0;
        this.coordinates = new Coordinates(latitude, longitude);
        this.maxDistance = maxDistance;
    }

    public void setMaximumDistance(double maxDistance){ this.maxDistance = maxDistance; }

    public Double getMaxDistance(){ return this.maxDistance; }

    public void setMinGrollies(int grollies){ minGrollies = grollies; }

    public Integer getMinGrollies(){ return minGrollies; }

    public Coordinates getCoordinates(){ return coordinates; }




}
