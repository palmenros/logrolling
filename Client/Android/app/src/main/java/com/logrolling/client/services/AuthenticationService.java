package com.logrolling.client.services;

import com.logrolling.client.controllers.Controller;
import com.logrolling.client.web.ErrorListener;
import com.logrolling.client.web.ResponseListener;
import com.logrolling.client.web.SuccessListener;

public class AuthenticationService {

    private static AuthenticationService instance;

    private String authToken;
    private String username;

    private AuthenticationService() {
    }

    public static AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    public void tryStoredTokenAuth(ResponseListener<Boolean> responseListener, ErrorListener errorListener) {
        if (PersistentStorageService.getInstance().has("authToken")) {

            authToken = PersistentStorageService.getInstance().get("authToken");

            //Has token, validate with database
            Controller.getInstance().getCurrentUser((user) -> {
                username = user.getUsername();
                responseListener.onResponse(true);
            }, errorListener);
        } else {
            responseListener.onResponse(false);
        }
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getAuthenticatedUsername() {
        return username;
    }

    public void signOut() {
        authToken = null;
        PersistentStorageService.getInstance().remove("authToken");
    }

    public void tryLogWithCredentials(String username, String password, SuccessListener successListener, ErrorListener errorListener) {
        Controller controller = Controller.getInstance();

        controller.login(username, password, (token) -> {
            this.authToken = token.getContent();

            PersistentStorageService.getInstance().put("authToken", this.authToken);

            controller.getCurrentUser((user) -> {
                this.username = user.getUsername();
                successListener.onSuccess();
            }, errorListener);

        }, errorListener);
    }
}
