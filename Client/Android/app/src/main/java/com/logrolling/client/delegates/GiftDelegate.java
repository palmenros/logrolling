package com.logrolling.client.delegates;

import com.logrolling.client.services.AuthenticationService;
import com.logrolling.client.services.SerializationService;
import com.logrolling.client.transfer.TransferGift;
import com.logrolling.client.transfer.TransferMessagePreview;
import com.logrolling.client.transfer.TransferPurchase;
import com.logrolling.client.web.ErrorListener;
import com.logrolling.client.web.ResponseListener;
import com.logrolling.client.web.SuccessListener;
import com.logrolling.client.web.WebServiceClient;

public class GiftDelegate {

    private WebServiceClient client;

    public GiftDelegate() {
        client = new WebServiceClient();
    }

    public void getAllGifts(ResponseListener<TransferGift[]> responseListener, ErrorListener errorListener) {
        client.getRequest(
                "gifts",
                null,
                SerializationService.getInstance().getResponseListener(TransferGift[].class, responseListener, errorListener),
                AuthenticationService.getInstance().getAuthToken(),
                errorListener
        );
    }

    public void purchaseGift(TransferPurchase transferPurchase, SuccessListener successListener, ErrorListener errorListener) {
        client.postRequest("gifts/@purchase", transferPurchase,
                WebServiceClient.getSuccessResponseListener(successListener),
                AuthenticationService.getInstance().getAuthToken(),
                errorListener);
    }
}
