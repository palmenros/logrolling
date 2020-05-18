package com.logrolling.server.services.favors;

public class Coordinates {

    double latitude;
    double longitude;

    public Coordinates() {

    }

    public Coordinates(double latitude, double longitude) {

        this.latitude = latitude;
        this.longitude = longitude;

    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
