package com.logrolling.client.delegates;

import com.google.gson.Gson;
import com.logrolling.client.data.Settings;
import com.logrolling.client.exceptions.RequestException;
import com.logrolling.client.services.AuthenticationService;
import com.logrolling.client.services.SerializationService;
import com.logrolling.client.transfer.TransferChat;
import com.logrolling.client.transfer.TransferMessage;
import com.logrolling.client.transfer.TransferMessagePreview;
import com.logrolling.client.web.ErrorListener;
import com.logrolling.client.web.ResponseListener;
import com.logrolling.client.web.SuccessListener;
import com.logrolling.client.web.WebServiceClient;

import java.util.List;

public class ChatDelegate {

    private WebServiceClient client;

    public ChatDelegate() {
        client = new WebServiceClient();
    }

    public void getChatWithUser(String username, ResponseListener<TransferChat> responseListener, ErrorListener errorListener) {
        client.getRequest(
                "chats/" + username,
                null,
                SerializationService.getInstance().getResponseListener(TransferChat.class, responseListener, errorListener),
                AuthenticationService.getInstance().getAuthToken(),
                errorListener
        );
    }

    public void getChatPreviews(ResponseListener<TransferMessagePreview[]> responseListener, ErrorListener errorListener) {
        client.getRequest(
                "chats/interactions",
                null,
                SerializationService.getInstance().getResponseListener(TransferMessagePreview[].class, responseListener, errorListener),
                AuthenticationService.getInstance().getAuthToken(),
                errorListener
        );
    }

    public void sendMessage(TransferMessage transferMessage, SuccessListener successListener, ErrorListener errorListener) {
        client.postRequest("chats/", transferMessage, WebServiceClient.getSuccessResponseListener(successListener), AuthenticationService.getInstance().getAuthToken(), errorListener);
    }

}
