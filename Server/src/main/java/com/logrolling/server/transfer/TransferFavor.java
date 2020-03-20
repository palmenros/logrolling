package com.logrolling.server.transfer;

import com.logrolling.server.model.Favor;

public class TransferFavor {
    private int id;
    private String creator;
    private String title;
    private String description;
    private Integer dueTime;

    public TransferFavor(int id, String creator,  String title, String description, Integer dueTime) {
        this.id = id;
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.dueTime = dueTime;
    }

    public TransferFavor(Favor favor) {
        this(favor.getId(), favor.getCreator(), favor.getTitle(), favor.getDescription(), favor.getDueTime());
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

}
