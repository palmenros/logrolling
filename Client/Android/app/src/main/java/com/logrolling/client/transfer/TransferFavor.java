package com.logrolling.client.transfer;

import android.location.Location;

import com.logrolling.client.services.LocationService;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransferFavor {
    private int id;
    private String creator;
    private String title;
    private String description;
    private Integer dueTime;
    private int reward;
    private Coordinates coordinates;
    private String worker;
    private boolean completed;
    //Rest of attributes from favor

    public TransferFavor(int id, String creator, String title, String description, Integer dueTime, int reward, Coordinates coordinates, String worker, boolean completed) {
        this.id = id;
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.dueTime = dueTime;
        this.reward = reward;
        this.coordinates = coordinates;
        this.worker = worker;
        this.completed = completed;
    }

    public TransferFavor(String creator, String title, String description, Integer dueTime, int reward, Coordinates coordinates, String worker, boolean completed) {
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.dueTime = dueTime;
        this.reward = reward;
        this.coordinates = coordinates;
        this.worker = worker;
        this.completed = completed;
    }

    public TransferFavor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDueTime() {
        return dueTime;
    }

    public void setDueTime(Integer dueTime) {
        this.dueTime = dueTime;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean b) {
        this.completed = b;
    }

    public String getDistance() {
        LocationService locationService = LocationService.getInstance();
        return locationService.formatDistance(locationService.getDistanceFromCoordinates(coordinates));
    }

    public String getAddress() {
        return LocationService.getInstance().getAddressFromCoordinates(coordinates);
    }

    public String getFormattedDueTime() {
        PrettyTime p = new PrettyTime();
        return p.format(new Date(dueTime * 1000L));
    }
}

