package com.logrolling.server.services.favors;

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
        awarded = false;
        minDate = null;

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

    public void setAwarded(boolean awarded){ this.awarded = awarded; }

    public boolean getAwarded(){ return awarded; }

    public Date getMinDate(){ return minDate; }

    public void setMinDate(Date minDate){ this.minDate = minDate; }




}
