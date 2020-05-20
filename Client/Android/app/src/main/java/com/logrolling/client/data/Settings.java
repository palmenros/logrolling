package com.logrolling.client.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Settings {
    //private static final String baseURL = "http://192.168.0.100:8080/";
    //private static final String baseURL = "http://192.168.0.100:8080/Server_war_exploded/";

    private static final String baseURL = "http://51.136.44.210:8080/";
    private static final String termsAndConditionsURL = "http://palmenros.github.io/logrolling/legal.txt";

    public static String getBaseURL() {
        return baseURL;
    }

    public static String getTermsAndConditionsURL() {
        return termsAndConditionsURL;
    }
}

