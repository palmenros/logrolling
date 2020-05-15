package com.logrolling.client.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Settings {
    private static final String baseURL = "http://192.168.0.100:8080/";
    //private static final String baseURL = "http://192.168.0.100:8080/Server_war_exploded/";

    public static String getBaseURL() {
        return baseURL;
    }

}

