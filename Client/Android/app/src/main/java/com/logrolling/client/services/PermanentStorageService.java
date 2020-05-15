package com.logrolling.client.services;

import android.content.Context;
import android.content.SharedPreferences;

public class PermanentStorageService {
    private static PermanentStorageService instance;
    private static final String preferenceFileKey = "LogrollingPersistentData";
    private SharedPreferences sharedPreferences;

    private PermanentStorageService(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
    }

    public static void createInstance(Context context) {
        instance = new PermanentStorageService(context);
    }

    public static PermanentStorageService getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Permanent Storage Service not initialized yet");
        }
        return instance;
    }

    public void put(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public boolean has(String key) {
        return sharedPreferences.getString(key, null) != null;
    }

    public String get(String key) {
        return sharedPreferences.getString(key, null);
    }
}
