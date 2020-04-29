package com.logrolling.server.database.factories;

import com.logrolling.server.database.DatabaseException;

public abstract class Factory<T> {
    abstract T createInstance();
}
