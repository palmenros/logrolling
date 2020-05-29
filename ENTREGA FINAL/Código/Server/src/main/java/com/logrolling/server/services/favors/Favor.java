package com.logrolling.server.services.favors;

import com.logrolling.server.exceptions.AuthenticationException;
import com.logrolling.server.exceptions.UnauthorizedException;
import com.logrolling.server.integration.favor.FavorDAO;
import com.logrolling.server.services.authentication.AuthenticationService;
import com.logrolling.server.services.users.User;
import com.logrolling.server.integration.users.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class Favor {

    private int id;
    private String creator;
    private String title;
    private String description;
    private Integer dueTime;
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

    public Favor(String creator, String title, String description, Integer dueTime, int reward, double latitude, double longitude, boolean completed) {
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.dueTime = dueTime;
        this.reward = reward;
        this.coordinates = new Coordinates(latitude, longitude);
        this.worker = null;
        this.completed = completed;
    }

    public Favor(int id, String creator, String title, String description, Integer dueTime, int reward, double latitude, double longitude, String worker, boolean completed) {
        this.id = id;
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.dueTime = dueTime;
        this.reward = reward;
        this.coordinates = new Coordinates(latitude, longitude);
        this.worker = worker;
        this.completed = completed;
    }

    public Favor(String creator, String title, String description, Integer dueTime, int reward, double latitude, double longitude, String worker, boolean completed) {
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.dueTime = dueTime;
        this.reward = reward;
        this.coordinates = new Coordinates(latitude, longitude);
        this.worker = worker;
        this.completed = completed;
    }

    public Favor(TransferFavor f) {
        this.creator = f.getCreator();
        this.title = f.getTitle();
        this.description = f.getDescription();
        this.dueTime = f.getDueTime();
        this.reward = f.getReward();
        this.coordinates = f.getCoordinates();
        this.worker = f.getWorker();
        this.completed = f.getCompleted();
    }

    public static TransferFavor getFavorById(int id) {
        return new TransferFavor(FavorDAO.getFavorById(id));
    }

    public static TransferFavor getLatestCreatedFavor(String token) {
        String username = AuthenticationService.authenticateWithToken(token);
        return new TransferFavor(FavorDAO.getLatestFavorFromUser(username));
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

    public Double getLatCoord() {
        return coordinates.getLatitude();
    }

    public Double getLongCoord() {
        return coordinates.getLongitude();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double latitude, double longitude) {
        coordinates.setLatitude(latitude);
        coordinates.setLongitude(longitude);
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

    public static List<TransferFavor> getAvailableFavors(String token) {
        String username = AuthenticationService.authenticateWithToken(token);
        List<Favor> favors = FavorDAO.getAvailableFavors(username);
        List<TransferFavor> transfers = new ArrayList<TransferFavor>();
        for (Favor f : favors) {
            transfers.add(new TransferFavor(f));
        }
        return transfers;
    }

    public static List<TransferFavor> getFavorsFromFilter(String token, Filter filter) {
        String username = AuthenticationService.authenticateWithToken(token);
        List<Favor> favors = FavorDAO.getFavorsByFilter(username, filter);
        List<TransferFavor> transfers = new ArrayList<TransferFavor>();
        for (Favor f : favors) {
            transfers.add(new TransferFavor(f));
        }
        return transfers;
    }

    public static List<TransferFavor> getFavorsFromUser(String token) {
        String username = AuthenticationService.authenticateWithToken(token);
        List<Favor> favors = FavorDAO.getFavorsFromUsername(username);
        List<TransferFavor> transfers = new ArrayList<TransferFavor>();
        for (Favor f : favors) {
            transfers.add(new TransferFavor(f));
        }
        return transfers;
    }

    public static List<TransferFavor> getAwardedFavors(String token) {
        String username = AuthenticationService.authenticateWithToken(token);
        List<TransferFavor> transfers = new ArrayList<TransferFavor>();
        List<Favor> favors = FavorDAO.getAwardedFavors(username);
        for (Favor f : favors) {
            transfers.add(new TransferFavor(f));
        }
        return transfers;
    }

    public static void completeFavor(int id, String token) {
        String username = AuthenticationService.authenticateWithToken(token);
        Favor favor = FavorDAO.getFavorById(id);

        if (favor.getCreator().equals(username)) {
            //Favor was created by current user
            favor.setCompleted(true);

            //Give grollies to worker
            User worker = UserDAO.getUserByName(favor.getWorker());
            UserDAO.updateUserGrollies(favor.getWorker(), worker.getGrollies() + favor.getReward());

            FavorDAO.updateFavor(id, favor);
        } else {
            throw new UnauthorizedException();
        }
    }

    public static void addFavor(TransferFavor f, String token) {
        String username = AuthenticationService.authenticateWithToken(token);
        if (f.getCreator().equals(username)) {
            //Favor was created by current user
            FavorDAO.createFavor(new Favor(f));
        } else {
            throw new UnauthorizedException();
        }
    }

    public static void updateFavor(TransferFavor f, int id, String token) {
        String username = AuthenticationService.authenticateWithToken(token);
        if (FavorDAO.getFavorById(id).getCreator().equals(username)) {
            //Favor was created by current user
            FavorDAO.updateFavor(id, new Favor(f));
        } else {
            throw new UnauthorizedException();
        }
    }

    public static void deleteFavor(int id, String token) {
        String username = AuthenticationService.authenticateWithToken(token);
        Favor favor = FavorDAO.getFavorById(id);
        if (favor.getCreator().equals(username)) {
            int reward = favor.getReward();

            //Favor was created by current user
            FavorDAO.deleteFavorFromId(id);

            //Give user back their grollies
            User user = UserDAO.getUserByName(username);
            UserDAO.updateUserGrollies(username, user.getGrollies() + reward);

        } else {
            throw new UnauthorizedException();
        }
    }

    public static void doFavor(int id, String token) {

        Favor favor = FavorDAO.getFavorById(id);
        String username = AuthenticationService.authenticateWithToken(token);

        if (favor.getWorker() != null || username.equals(favor.getCreator())) {
            throw new AuthenticationException();
        }

        favor.setWorker(username);
        FavorDAO.updateFavor(id, favor);
    }

}
