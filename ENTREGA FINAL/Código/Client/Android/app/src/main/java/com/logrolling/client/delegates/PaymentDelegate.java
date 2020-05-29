package com.logrolling.client.delegates;

import com.logrolling.client.services.AuthenticationService;
import com.logrolling.client.services.SerializationService;
import com.logrolling.client.transfer.TransferPaymentToken;
import com.logrolling.client.transfer.TransferTransaction;
import com.logrolling.client.web.ErrorListener;
import com.logrolling.client.web.ResponseListener;
import com.logrolling.client.web.SuccessListener;
import com.logrolling.client.web.WebServiceClient;

public class PaymentDelegate {

    private WebServiceClient client;

    public PaymentDelegate() {
        client = new WebServiceClient();
    }

    public void getPaymentClientToken(ResponseListener<TransferPaymentToken> responseListener, ErrorListener errorListener) {
        client.getRequest("payment",
                null,
                SerializationService.getInstance().getResponseListener(TransferPaymentToken.class, responseListener, errorListener),
                null,
                errorListener);
    }

    public void makeTransaction(TransferTransaction transferTransaction, SuccessListener successListener, ErrorListener errorListener) {
        client.postRequest("payment",
                transferTransaction,
                WebServiceClient.getSuccessResponseListener(successListener),
                AuthenticationService.getInstance().getAuthToken(),
                errorListener);
    }

}
