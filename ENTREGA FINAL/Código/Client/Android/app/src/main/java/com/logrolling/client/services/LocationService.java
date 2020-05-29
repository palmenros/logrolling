package com.logrolling.client.services;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.logrolling.client.exceptions.RequestException;
import com.logrolling.client.transfer.Coordinates;
import com.logrolling.client.web.ErrorListener;
import com.logrolling.client.web.SuccessListener;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

public class LocationService {

    private static LocationService instance;

    private LocationRequest locationRequest;
    private FusedLocationProviderClient fusedLocationClient;
    private Coordinates lastCoordinates = null;
    private String lastProvider = null;
    private Geocoder geocoder;

    private SuccessListener successListener;
    private ErrorListener errorListener;

    private LocationService(Activity activity, SuccessListener onLocationReady, ErrorListener onErrorListener) {

        geocoder = new Geocoder(activity.getApplicationContext(), Locale.getDefault());

        successListener = onLocationReady;
        errorListener = onErrorListener;

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        locationRequest = createLocationRequest();

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        SettingsClient client = LocationServices.getSettingsClient(activity);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(activity, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // All location settings are satisfied. The client can initialize
                // location requests here.

                fusedLocationClient.getLastLocation().addOnSuccessListener((location) -> {
                    if (location != null) {
                        lastCoordinates = new Coordinates(location.getLatitude(), location.getLongitude());
                        lastProvider = location.getProvider();

                        successListener.onSuccess();
                    }

                    fusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback() {
                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            if (locationResult == null) {
                                if (lastCoordinates == null) {
                                    errorListener.onError(new RequestException("Could not get location"));
                                }

                                return;
                            }

                            boolean sendSuccess = lastCoordinates == null;

                            for (Location location : locationResult.getLocations()) {
                                lastCoordinates = new Coordinates(location.getLatitude(), location.getLongitude());
                            }

                            if (sendSuccess) {
                                successListener.onSuccess();
                            }
                        }
                    }, Looper.getMainLooper());

                }).addOnFailureListener((error) -> {
                    onErrorListener.onError(new RequestException(error.getMessage()));
                });

            }
        });

        task.addOnFailureListener(activity, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                onErrorListener.onError(new RequestException("Location settings no satisfied"));
            }
        });

    }

    public static void createInstance(Activity activity, SuccessListener onLocationReady, ErrorListener onErrorListener) {
        instance = new LocationService(activity, onLocationReady, onErrorListener);
    }

    protected LocationRequest createLocationRequest() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        return locationRequest;
    }

    public static LocationService getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Location Service used before initialized");
        }
        return instance;
    }

    public Coordinates getLocation() {
        return lastCoordinates;
    }

    public double getDistanceFromCoordinates(Coordinates coordinates) {
        Coordinates currentLocation = getLocation();

        double latA = currentLocation.getLatitude();
        double longA = currentLocation.getLongitude();
        double latB = coordinates.getLatitude();
        double longB = coordinates.getLongitude();

        return 111.111 * 1000 * Math.toDegrees(Math.acos(Math.min(1.0, Math.cos(Math.toRadians(latA))
                * Math.cos(Math.toRadians(latB))
                * Math.cos(Math.toRadians(longA - longB))
                + Math.sin(Math.toRadians(latA))
                * Math.sin(Math.toRadians(latB)))));
    }

    public String getAddressFromCoordinates(Coordinates coordinates) {
        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(coordinates.getLatitude(), coordinates.getLongitude(), 1);
            if (addresses.isEmpty()) {
                throw new Exception("Empty list");
            }
            return addresses.get(0).getAddressLine(0);
        } catch (Exception e) {
            return String.format("(%f N, %f E)", coordinates.getLatitude(), coordinates.getLongitude());
        }
    }

    //Expects distance in meters
    public String formatDistance(double distance) {

        String unit = "m";
        String numberRepresentation = Integer.valueOf((int) distance).toString();

        if (distance >= 1000) {
            distance /= 1000;
            unit = "km";
            numberRepresentation = String.format("%.1f", distance);
        }

        return "A " + numberRepresentation + " " + unit + " de ti";
    }
}
