package com.logrolling.client.web;

public interface ResponseListener<Data> {
    public void onResponse(Data data);
}
