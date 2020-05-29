package com.logrolling.client.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logrolling.client.exceptions.RequestException;
import com.logrolling.client.web.ErrorListener;
import com.logrolling.client.web.ResponseListener;

public class SerializationService {

    private static SerializationService instance;

    private SerializationService() {
    }

    public static SerializationService getInstance() {
        if (instance == null) {
            instance = new SerializationService();
        }
        return instance;
    }

    public Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        return builder.create();
    }

    public <Data> Data deserializeData(String json, Class<Data> cls, ErrorListener errorListener) {
        Gson gson = getGson();
        try {
            return gson.fromJson(json, cls);
        } catch (Exception e) {
            errorListener.onError(new RequestException(e));
            return null;
        }
    }

    public <T> ResponseListener<String> getResponseListener(Class<T> cls, ResponseListener<T> responseListener, ErrorListener errorListener) {
        return new ResponseListener<String>() {
            @Override
            public void onResponse(String str) {
                T data = SerializationService.getInstance().deserializeData(str, cls, errorListener);
                if (data != null) {
                    responseListener.onResponse(data);
                }
            }
        };
    }

}
