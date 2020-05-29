package com.logrolling.client.delegates;

import com.logrolling.client.services.AuthenticationService;
import com.logrolling.client.web.ErrorListener;
import com.logrolling.client.web.SuccessListener;
import com.logrolling.client.web.WebServiceClient;

public class AdDelegate {
    private WebServiceClient client;

    public AdDelegate() {
        client = new WebServiceClient();
    }

    public void giveUserVideoReward(SuccessListener successListener, ErrorListener errorListener) {
        client.postRequest("ad",
                null,
                WebServiceClient.getSuccessResponseListener(successListener),
                AuthenticationService.getInstance().getAuthToken(),
                errorListener);
    }

}
