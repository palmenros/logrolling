package com.logrolling.client.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class WebRequestQueue {

    //This class is supposed to exist during all application execution, so it is not a memory leak
    @SuppressLint("StaticFieldLeak")
    private static WebRequestQueue instance;

    //This class is supposed to exist during all application execution, so it is not a memory leak
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    //Request queue that will manage all web requests
    private RequestQueue requestQueue;

    private WebRequestQueue(Context context) {
        WebRequestQueue.context = context;

        //Pass application context so request queue lives during all application execution
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static void createInstance(Context context) {
        instance = new WebRequestQueue(context);
    }

    public static WebRequestQueue getInstance() {
        if(instance == null) {
            throw new IllegalStateException("WebRequestQueue getInstance was called before instance creation");
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
