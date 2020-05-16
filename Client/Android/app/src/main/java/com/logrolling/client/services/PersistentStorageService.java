package com.logrolling.client.services;

import android.content.Context;
import android.content.SharedPreferences;

public class PersistentStorageService {
    private static PersistentStorageService instance;
    private static final String preferenceFileKey = "LogrollingPersistentData";
    private SharedPreferences sharedPreferences;

    private PersistentStorageService(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
    }

    public static void createInstance(Context context) {
        instance = new PersistentStorageService(context);
    }

    public static PersistentStorageService getInstance() {
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

    public void remove(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public boolean has(String key) {
        return sharedPreferences.getString(key, null) != null;
    }

    public String get(String key) {
        return sharedPreferences.getString(key, null);
    }
}
