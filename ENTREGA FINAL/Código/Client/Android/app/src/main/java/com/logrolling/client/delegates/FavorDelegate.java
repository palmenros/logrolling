package com.logrolling.client.delegates;

import com.logrolling.client.services.AuthenticationService;
import com.logrolling.client.services.SerializationService;
import com.logrolling.client.transfer.Filter;
import com.logrolling.client.transfer.TransferFavor;
import com.logrolling.client.web.ErrorListener;
import com.logrolling.client.web.ResponseListener;
import com.logrolling.client.web.SuccessListener;
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

    public void getCreatedFavors(ResponseListener<TransferFavor[]> responseListener, ErrorListener errorListener) {
        client.getRequest(
                "favors/user",
                null,
                SerializationService.getInstance().getResponseListener(TransferFavor[].class, responseListener, errorListener),
                AuthenticationService.getInstance().getAuthToken(),
                errorListener
        );
    }

    public void doFavor(int favorId, SuccessListener successListener, ErrorListener errorListener) {
        client.putRequest(
                "favors/" + favorId + "/@do",
                null,
                WebServiceClient.getSuccessResponseListener(successListener),
                AuthenticationService.getInstance().getAuthToken(),
                errorListener
        );
    }

    public void completeFavor(int favorId, SuccessListener successListener, ErrorListener errorListener) {
        client.putRequest(
                "favors/" + favorId + "/@complete",
                null,
                WebServiceClient.getSuccessResponseListener(successListener),
                AuthenticationService.getInstance().getAuthToken(),
                errorListener
        );
    }

    public void getFavorsToBeDone(ResponseListener<TransferFavor[]> responseListener, ErrorListener errorListener) {
        client.getRequest(
                "favors/awarded",
                null,
                SerializationService.getInstance().getResponseListener(TransferFavor[].class, responseListener, errorListener),
                AuthenticationService.getInstance().getAuthToken(),
                errorListener
        );
    }

    public void createFavor(TransferFavor favor, SuccessListener successListener, ErrorListener errorListener) {
        client.postRequest("favors",
                favor,
                WebServiceClient.getSuccessResponseListener(successListener),
                AuthenticationService.getInstance().getAuthToken(),
                errorListener);
    }

    public void updateFavor(TransferFavor newFavor, int favorId, SuccessListener successListener, ErrorListener errorListener) {
        client.putRequest("favors/" + favorId,
                newFavor,
                WebServiceClient.getSuccessResponseListener(successListener),
                AuthenticationService.getInstance().getAuthToken(),
                errorListener);
    }

    public void deleteFavor(int favorId, SuccessListener successListener, ErrorListener errorListener) {
        client.deleteRequest("favors/" + favorId,
                null,
                WebServiceClient.getSuccessResponseListener(successListener),
                AuthenticationService.getInstance().getAuthToken(),
                errorListener);
    }

    public void getFavorById(int favorId, ResponseListener<TransferFavor> responseListener, ErrorListener errorListener) {
        client.getRequest("favors/" + favorId,
                null,
                SerializationService.getInstance().getResponseListener(TransferFavor.class, responseListener, errorListener),
                null,
                errorListener);
    }

    public void getLatestFavor(ResponseListener<TransferFavor> responseListener, ErrorListener errorListener) {
        client.getRequest("favors/last",
                null,
                SerializationService.getInstance().getResponseListener(TransferFavor.class, responseListener, errorListener),
                AuthenticationService.getInstance().getAuthToken(),
                errorListener
        );
    }
}
