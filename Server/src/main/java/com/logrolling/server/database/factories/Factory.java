package com.logrolling.server.database.factories;

public abstract class Factory<T> {
    abstract T createInstance();
}
