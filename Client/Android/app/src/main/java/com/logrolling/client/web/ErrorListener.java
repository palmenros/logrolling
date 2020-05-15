package com.logrolling.client.web;

import com.logrolling.client.exceptions.RequestException;

public interface ErrorListener {
    void onError(RequestException ex);
}