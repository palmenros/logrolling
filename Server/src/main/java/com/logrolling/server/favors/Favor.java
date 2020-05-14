package com.logrolling.server.favors;

import com.logrolling.server.exceptions.UnauthorizedException;

import java.util.ArrayList;
import java.util.List;

public class Favor {

    private int id;
    private String creator;
    private String title;
    private String description;
    private  Integer dueTime;
    private int reward;
    private Coordinates coordinates;
    private String worker;
    private boolean completed;

    public Favor(int id, String creator, String title, String description, Integer dueTime, int reward, double latitude, double longitude) {
        this.id = id;
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.dueTime = dueTime;
        this.reward = reward;
        this.coordinates = new Coordinates(latitude, longitude);
        this.worker = null;
        this.completed = false;
    }

    public Favor(String creator, String title, String description, Integer dueTime, int reward, double latitude, double longitude) {
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.dueTime = dueTime;
        this.reward = reward;
        this.coordinates = new Coordinates(latitude, longitude);
        this.worker = null;
        this.completed = false;
    }

    public Favor(int id, String creator, String title, String description, Integer dueTime, int reward, double latitude, double longitude, String worker) {
        this.id = id;
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.dueTime = dueTime;
        this.reward = reward;
        this.coordinates = new Coordinates(latitude, longitude);
        this.worker = worker;
        this.completed = false;
    }

    public Favor(String creator, String title, String description, Integer dueTime, int reward, double latitude, double longitude, String worker) {
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.dueTime = dueTime;
        this.reward = reward;
        this.coordinates = new Coordinates(latitude, longitude);
        this.worker = worker;
        this.completed = false;
    }

    public Favor(TransferFavor f){
        this.creator = f.getCreator();
        this.title = f.getTitle();
        this.description = f.getDescription();
        this.dueTime = f.getDueTime();
        this.reward = f.getReward();
        this.coordinates = f.getCoordinates();
        this.worker = f.getWorker();
        this.completed = f.getCompleted();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescription(){ return description; }

    public void setDescription(String description){ this.description = description; }

    public Integer getDueTime(){ return dueTime; }

    public void setDueTime(Integer dueTime){ this.dueTime = dueTime; }

    public Integer getReward(){ return reward; }

    public void setReward(int reward){ this.reward = reward; }

    public Double getLatCoord(){ return coordinates.getLatitude(); }

    public Double getLongCoord(){return coordinates.getLongitude(); }

    public Coordinates getCoordinates(){ return coordinates; }

    public void setCoordinates(double latitude, double longitude){
        coordinates.setLatitude(latitude);
        coordinates.setLongitude(longitude);
    }

    public String getWorker(){ return worker; }

    public void setWorker(String worker){ this.worker = worker; }

    public boolean getCompleted(){ return completed; }

    public void setCompleted(boolean b){ this.completed = b; }

    public static List<TransferFavor> getAvailableFavors(String username) {
        List<Favor> favors = FavorManager.getAvailableFavors(username);
        List<TransferFavor> transfers = new ArrayList<TransferFavor>();
        for (Favor f : favors) {
            transfers.add(new TransferFavor(f));
        }
        return transfers;
    }

    public static List<TransferFavor> getFavorsFromFilter(String username, Filter filter){
        List<Favor> favors = FavorManager.getFavorsByFilter(username, filter);
        List<TransferFavor> transfers = new ArrayList<TransferFavor>();
        for (Favor f : favors) {
            transfers.add(new TransferFavor(f));
        }
        return transfers;
    }

    public static List<TransferFavor> getFavorsFromUser(String username){
        List<Favor> favors = FavorManager.getFavorsFromUsername(username);
        List<TransferFavor> transfers = new ArrayList<TransferFavor>();
        for (Favor f : favors) {
            transfers.add(new TransferFavor(f));
        }
        return transfers;
    }

    public static List<TransferFavor> getAwardedFavors(String username){
        List<TransferFavor> transfers = new ArrayList<TransferFavor>();
        List<Favor> favors = FavorManager.getAwardedFavors(username);
        for (Favor f : favors) {
            transfers.add(new TransferFavor(f));
        }
        return transfers;
    }

    public static void completeFavor(int id, String username){
        if(FavorManager.getFavorById(id).getCreator().equals(username)) {
            //Favor was created by current user
            FavorManager.completeFavor(id);
        } else {
            throw new UnauthorizedException();
        }
    }

    public static void addFavor(TransferFavor f, String username){
        if(f.getCreator().equals(username)) {
            //Favor was created by current user
            FavorManager.createFavor(new Favor(f));
        } else {
            throw new UnauthorizedException();
        }
    }

    public static void updateFavor(TransferFavor f, int id, String username){
        if(FavorManager.getFavorById(id).getCreator().equals(username)) {
            //Favor was created by current user
            FavorManager.updateFavor(id, new Favor(f));
        } else {
            throw new UnauthorizedException();
        }
    }

    public static void deleteFavor(int id, String username){
        if(FavorManager.getFavorById(id).getCreator().equals(username)) {
            //Favor was created by current user
            FavorManager.deleteFavorFromId(id);
        } else {
            throw new UnauthorizedException();
        }
    }

    public static void doFavor(int id, String username){
        FavorManager.doFavor(id, username);
    }

}