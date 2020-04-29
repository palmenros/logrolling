package com.logrolling.server.database.factories;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.DatabaseException;
import com.logrolling.server.database.MySQLDatabase;

/**
 * Database factory that constructs a new MySQLDatabase
 */
public class MySQLDatabaseFactory extends Factory<Database> {

    @Override
    Database createInstance() throws DatabaseException {
        return new MySQLDatabase();
    }
}
