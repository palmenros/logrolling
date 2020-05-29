package com.logrolling.server.database.migrations;

public interface Migration {

    /**
     * Creates tables necessary for model manager of given class
     */
    void migrate();

    /**
     * Fills tables affected by this migration with dummy data
     */
    void fillDummy();
}
