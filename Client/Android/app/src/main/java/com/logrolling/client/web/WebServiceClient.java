package com.logrolling.client.web;

import android.renderscript.ScriptGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logrolling.client.exceptions.RequestException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class WebServiceClient {

    public interface ResponseListener<InputObject> {
        void onResponse(InputObject obj);
    }

    public interface ErrorListener<InputObject> {
        void onError(RequestException ex);
    }

    public <InputObject> void getRequest(
            String url,
            InputObject input,
            final ResponseListener<String> responseListener,
            final String authenticationToken,
            final ErrorListener errorListener
    ) {

        String jsonString = null;
        if(input != null) {
            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            Gson gson = builder.create();

            jsonString = gson.toJson(input);
        }

        final String responseBody = jsonString;

        //TODO: Append URL with some based URL declared in settings
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String str) {
                        responseListener.onResponse(str);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (errorListener != null) {
                            errorListener.onError(new RequestException(error.getMessage()));
                        }
                    }
                }){

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    //Set header params
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("User-Agent", "Logrolling");
                    params.put("Content-Type", "application/json");

                    if(authenticationToken != null) {
                        params.put("token", authenticationToken);
                    }

                    return params;
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        if(responseBody == null) {
                            return new byte[]{};
                        } else {
                            return responseBody.getBytes("utf-8");
                        }
                    } catch (UnsupportedEncodingException e) {
                        VolleyLog.wtf("Unsopported encoding exception when sending request");
                        return null;
                    }
                }

        };
        WebRequestQueue.getInstance().addToRequestQueue(request);
    }


}
