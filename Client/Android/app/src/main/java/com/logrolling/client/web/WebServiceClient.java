package com.logrolling.client.web;

import android.renderscript.ScriptGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logrolling.client.exceptions.RequestException;

import org.json.JSONException;
import org.json.JSONObject;

public class WebServiceClient {

    public interface ResponseListener<InputObject> {
        void onResponse(InputObject obj);
    }

    public interface ErrorListener<InputObject> {
        void onError(RequestException ex);
    }

    public <InputObject, OutputObject> void getRequest(
            String url,
            InputObject input,
            final ResponseListener<OutputObject> responseListener,
            final Class<OutputObject> cls,
            final ErrorListener errorListener
    ) {

        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();

        final Gson gson = builder.create();

        String jsonString = gson.toJson(input);
        JSONObject inputJSON = null;
        try {
            inputJSON = new JSONObject(jsonString);
        } catch (JSONException e) {
            throw new IllegalStateException("JSONObject cannot be created from GSON generated JSON");
        }

        //TODO: Append URL with some based URL declared in settings
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                inputJSON,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject obj) {
                        OutputObject out = gson.fromJson(obj.toString(), cls);
                        responseListener.onResponse(out);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (errorListener != null) {
                            errorListener.onError(new RequestException(error.getMessage()));
                        }
                    }
                });

        WebRequestQueue.getInstance().addToRequestQueue(jsonObjectRequest);
    }


}
