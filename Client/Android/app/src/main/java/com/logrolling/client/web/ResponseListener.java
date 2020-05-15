package com.logrolling.client.web;

public interface ResponseListener<Data> {
    void onResponse(Data data);
}
