package com.logrolling.client.services;

import com.logrolling.client.transfer.Coordinates;

import java.text.DecimalFormat;

public class LocationService {

    //TODO: Implement

    static LocationService instance;

    private LocationService() {
    }

    public static LocationService getInstance() {
        if(instance == null) {
            instance = new LocationService();
        }
        return instance;
    }

    public double getDistanceFromCoordinates(Coordinates coordinates) {
        return 1500.0f;
    }

    public String getAddressFromCoordinates(Coordinates coordinates) {
        return "Calle Profesor Santamanes";
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
