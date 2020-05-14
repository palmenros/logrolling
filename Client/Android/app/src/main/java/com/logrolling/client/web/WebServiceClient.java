package com.logrolling.client.web;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class WebServiceClient {

    public interface ResponseListener<InputObject> {
        void onResponse(InputObject obj);
    }

    public <InputObject, OutputObject> OutputObject getRequest(String url, InputObject input, ResponseListener listener) {

        //TODO: Convert input object to inputJSON
        JSONObject inputJSON = null;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.GET,
            url,
            inputJSON,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject obj) {
                    //TODO: Implement
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //TODO: Implement
                }
            });

        //TODO: Change
        return null;
    }


}
