package com.logrolling.client.delegates;

import com.logrolling.client.services.SerializationService;
import com.logrolling.client.transfer.TransferCredentials;
import com.logrolling.client.transfer.TransferToken;
import com.logrolling.client.web.ErrorListener;
import com.logrolling.client.web.ResponseListener;
import com.logrolling.client.web.WebServiceClient;

public class TokenDelegate {
    private WebServiceClient client;

    public TokenDelegate() {
        client = new WebServiceClient();
    }

    public void login(TransferCredentials credentials, ResponseListener<TransferToken> responseListener, ErrorListener errorListener) {
        client.putRequest(
                "tokens",
                credentials,
                SerializationService.getInstance().getResponseListener(TransferToken.class, responseListener, errorListener),
                null,
                errorListener
        );
    }

}
