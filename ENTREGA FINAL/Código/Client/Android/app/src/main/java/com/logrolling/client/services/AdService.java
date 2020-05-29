package com.logrolling.client.services;

import android.content.Context;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.logrolling.client.exceptions.RequestException;
import com.logrolling.client.web.ErrorListener;
import com.logrolling.client.web.SuccessListener;

import static com.logrolling.client.BuildConfig.DEBUG;

public class AdService implements AudienceNetworkAds.InitListener {

    private SuccessListener successListener;
    private ErrorListener errorListener;

    private AdService(SuccessListener successListener, ErrorListener errorListener) {
        this.successListener = successListener;
        this.errorListener = errorListener;
    }

    public static void initialize(Context context, SuccessListener successListener, ErrorListener errorListener) {

        //TODO: Change test mode
        AdSettings.setTestMode(true);

        if (!AudienceNetworkAds.isInitialized(context)) {
            if (DEBUG) {
                AdSettings.turnOnSDKDebugger(context);
            }

            AudienceNetworkAds
                    .buildInitSettings(context)
                    .withInitListener(new AdService(successListener, errorListener))
                    .initialize();
        } else {
            //Already initialized
            successListener.onSuccess();
        }
    }

    @Override
    public void onInitialized(AudienceNetworkAds.InitResult result) {
        if (result.isSuccess()) {
            successListener.onSuccess();
        } else {
            errorListener.onError(new RequestException(result.getMessage()));
        }
    }
}