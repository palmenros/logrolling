package com.logrolling.client.services;

import com.logrolling.client.transfer.Coordinates;

import java.text.DecimalFormat;

public class LocationService {

    //TODO: Implement

    private static LocationService instance;

    private LocationService() {
    }

    public static LocationService getInstance() {
        if(instance == null) {
            instance = new LocationService();
        }
        return instance;
    }

    public Coordinates getLocation() {
        //TODO: Implement
        return new Coordinates(	40.384282, -3.938019);
    }

    public double getDistanceFromCoordinates(Coordinates coordinates) {
        //TODO: Implement
        return 1500.0f;
    }

    public String getAddressFromCoordinates(Coordinates coordinates) {
        //TODO: Implement
        return "Calle Profesor Santamanes";
    }

    public Coordinates getCoordinatesFromAddress(String address) {
        //TODO: Implement
        return getLocation();
    }

    //Expects distance in meters
    public String formatDistance(double distance) {

        String unit = "m";
        String numberRepresentation = Integer.valueOf((int)distance).toString();

        if(distance >= 1000) {
            distance /= 1000;
            unit = "km";
            numberRepresentation = String.format("%.1f", distance);
        }

        return "A " + numberRepresentation + " " + unit + " de ti";
    }

}
