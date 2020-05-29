package com.logrolling.client.delegates;

import com.logrolling.client.services.AuthenticationService;
import com.logrolling.client.services.SerializationService;
import com.logrolling.client.transfer.TransferCredentials;
import com.logrolling.client.transfer.TransferUser;
import com.logrolling.client.web.ErrorListener;
import com.logrolling.client.web.ResponseListener;
import com.logrolling.client.web.SuccessListener;
import com.logrolling.client.web.WebServiceClient;

public class UserDelegate {

    private WebServiceClient client;

    public UserDelegate() {
        client = new WebServiceClient();
    }

    public void getLoggedUser(ResponseListener<TransferUser> responseListener, ErrorListener errorListener) {
        client.getRequest("users",
                null,
                SerializationService.getInstance().getResponseListener(TransferUser.class, responseListener, errorListener),
                AuthenticationService.getInstance().getAuthToken(),
                errorListener
        );
    }

    public void getUserByUsername(String username, ResponseListener<TransferUser> responseListener, ErrorListener errorListener) {
        client.getRequest("users/" + username,
                null,
                SerializationService.getInstance().getResponseListener(TransferUser.class, responseListener, errorListener),
                null,
                errorListener
        );
    }

    public void registerUser(TransferCredentials transferCredentials, SuccessListener successListener, ErrorListener errorListener) {
        client.postRequest("users/",
                transferCredentials,
                WebServiceClient.getSuccessResponseListener(successListener),
                null,
                errorListener
        );
    }

    public void updateUser(TransferCredentials transferCredentials, SuccessListener successListener, ErrorListener errorListener) {
        client.putRequest("users/",
                transferCredentials,
                WebServiceClient.getSuccessResponseListener(successListener),
                AuthenticationService.getInstance().getAuthToken(),
                errorListener
        );
    }

    public void deleteUser(SuccessListener successListener, ErrorListener errorListener) {
        client.deleteRequest("users/", null,
                WebServiceClient.getSuccessResponseListener(successListener),
                AuthenticationService.getInstance().getAuthToken(),
                errorListener);
    }
}
