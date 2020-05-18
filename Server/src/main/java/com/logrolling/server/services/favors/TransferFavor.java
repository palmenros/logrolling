package com.logrolling.server.services.favors;

public class TransferFavor {
    private int id;
    private String creator;
    private String title;
    private String description;
    private Integer dueTime;    //Seconds from 1970
    private int reward;
    private Coordinates coordinates;
    private String worker;
    private boolean completed;

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

    public TransferFavor(Favor favor) {
        this(favor.getId(), favor.getCreator(), favor.getTitle(), favor.getDescription(), favor.getDueTime(), favor.getReward(), favor.getCoordinates(), favor.getWorker(), favor.getCompleted());
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

}
