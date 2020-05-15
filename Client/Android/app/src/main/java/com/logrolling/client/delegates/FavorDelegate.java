package com.logrolling.client.delegates;

import com.logrolling.client.services.AuthenticationService;
import com.logrolling.client.services.SerializationService;
import com.logrolling.client.transfer.Filter;
import com.logrolling.client.transfer.TransferFavor;
import com.logrolling.client.web.ErrorListener;
import com.logrolling.client.web.ResponseListener;
import com.logrolling.client.web.WebServiceClient;

public class FavorDelegate {

    private WebServiceClient client;

    public FavorDelegate() {
        client = new WebServiceClient();
    }

    public void getAvailableFavorsFiltered(Filter filter, ResponseListener<TransferFavor[]> responseListener, ErrorListener errorListener) {
        client.postRequest(
                "favors/filter",
                filter,
                 SerializationService.getInstance().getResponseListener(TransferFavor[].class, responseListener, errorListener),
                 AuthenticationService.getInstance().getAuthToken(),
                 errorListener
         );
    }

}
