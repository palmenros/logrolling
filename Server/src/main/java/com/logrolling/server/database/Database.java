package com.logrolling.server.database;

public abstract class Database {

    private static Database instance = null;

    protected static void setInstance(Database newInstance) {
        instance = newInstance;
    }

    public Database getInstance() {
        if(instance == null) {
            throw new NullPointerException("Database instance is null");
        }
        return instance;
    }

    //TODO: Define abstract methods for database interaction

}
