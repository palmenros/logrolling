package com.logrolling.client.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
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

    //Image loader that will be used for NetworkImageView
    private ImageLoader imageLoader;

    private WebRequestQueue(Context context) {
        WebRequestQueue.context = context;

        //Pass application context so request queue lives during all application execution
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        //Initialize imageLoader
        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });

    }

    //Creates a new instance
    public static synchronized void createInstance(Context context) {
        //TODO: Maybe uncomment this to prevent recreation. However, it is useful for quick Main Activity reload
        //if(instance != null) {
        //    throw new IllegalStateException("WebRequestQueue instance is already created");
        //}

        instance = new WebRequestQueue(context);
    }

    public static synchronized WebRequestQueue getInstance() {
        if (instance == null) {
            throw new IllegalStateException("WebRequestQueue getInstance was called before instance creation");
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
