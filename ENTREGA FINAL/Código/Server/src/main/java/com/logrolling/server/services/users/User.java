package com.logrolling.server.services.users;

import com.logrolling.server.integration.users.UserDAO;
import com.logrolling.server.services.authentication.AuthenticationService;
import com.logrolling.server.integration.authentication.TokenDAO;

import java.util.ArrayList;
import java.util.List;

public class User {

    static final int INITIAL_GROLLIES = 100;

    private int id;
    private String username;
    private String password;
    private int grollies;

    public User(int id, String username, String password, int grollies) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.grollies = grollies;
    }

    public User(String username, String password, int grollies) {
        this.username = username;
        this.password = password;
        this.grollies = grollies;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.grollies = INITIAL_GROLLIES;
    }

    public static TransferUser getLoggedUser(String token) {
        String username = AuthenticationService.authenticateWithToken(token);
        return getUserByName(username);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGrollies() {
        return grollies;
    }

    public void setGrollies(int grollies) {
        this.grollies = grollies;
    }

    public static List<TransferUser> getUsers() {
        List<TransferUser> transferList = new ArrayList<TransferUser>();

        for (User u : UserDAO.getAllUsers()) {
            transferList.add(new TransferUser(u));
        }
        return transferList;
    }

    public static void createUser(String username, String password) {
        UserDAO.createUser(new User(username, password));
    }

    public static void updateUserByName(String token, String password) {
        String old = AuthenticationService.authenticateWithToken(token);
        UserDAO.updateUserPassword(old, password);
    }

    public static TransferUser getUserByName(String username) {
        return new TransferUser(UserDAO.getUserByName(username));
    }

    public static void deleteUser(String token) {
        String username = AuthenticationService.authenticateWithToken(token);
        UserDAO.deleteUserByName(username);

        //Delete all auth tokens corresponding to deleted user 
        TokenDAO.deleteAllTokensFromUser(username);
    }

}
