package com.logrolling.server.database.factories;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.DatabaseException;

import javax.xml.crypto.Data;

/**
 * Factory for database. Should be used to create new databases, to ensure
 * that in the future different databases could be used
 */
public class DatabaseFactory {

    private static Factory<Database> factory = null;

    public synchronized static Database createInstance() throws DatabaseException {
        if (factory == null) {
            throw new IllegalStateException("No database factory has been set yet");
        }
        return factory.createInstance();
    }

    public static void setFactory(Factory<Database> newFactory) {
        factory = newFactory;
    }

}
