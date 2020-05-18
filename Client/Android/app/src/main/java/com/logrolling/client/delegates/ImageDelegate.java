package com.logrolling.client.delegates;

import com.logrolling.client.data.Settings;
import com.logrolling.client.services.AuthenticationService;
import com.logrolling.client.web.ErrorListener;
import com.logrolling.client.web.SuccessListener;
import com.logrolling.client.web.WebServiceClient;

public class ImageDelegate {

    private WebServiceClient client;

    public ImageDelegate() {
        client = new WebServiceClient();
    }

    public String getUserImageURL(String username) {
        return Settings.getBaseURL() + "images/user/" + username;
    }

    public String getFavorImageURL(int favorId) {
        return Settings.getBaseURL() + "images/favor/" + favorId;
    }

    public String getUncheckedImageURL(int favorId) {
        return Settings.getBaseURL() + "images/unchecked/favor/" + favorId;
    }

    public String getGiftImageURL(String giftName) {
        return Settings.getBaseURL() + "images/gift/" + giftName;
    }

    public void uploadUserImage(
            byte[] image,
            SuccessListener successListener,
            ErrorListener errorListener
    ) {
        client.postBytes("images/user", image, successListener, AuthenticationService.getInstance().getAuthToken(), errorListener);
    }

    public void uploadFavorImage(
            byte[] image,
            int favorId,
            SuccessListener successListener,
            ErrorListener errorListener
    ) {
        client.postBytes("images/favor/" + favorId, image, successListener, null, errorListener);
    }


}
