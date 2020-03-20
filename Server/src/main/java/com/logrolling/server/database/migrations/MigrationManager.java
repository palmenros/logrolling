package com.logrolling.server.database.migrations;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.factories.DatabaseFactory;

import java.util.ArrayList;
import java.util.List;

public class MigrationManager {

    /**
     * List of migrations to execute.
     * Executed in declaration order
     */
    private static Migration[] migrations = new Migration[]{
        new UserMigration(),
        new FavorMigration()
    };

    /**
     *   A migration is a way to keep databases clean and testable between developers.
     *   Every time the server is executed during development, all tables will be recreated
     *   and filled with dummy data.
     */
    public synchronized static void migrate() {
        //Delete all tables
        Database database = DatabaseFactory.createInstance();
        database.deleteAllTables();
        database.close();

        for(Migration migration : migrations) {
            migration.migrate();
        }
    }

    public synchronized static void fillDummy() {
        for(Migration migration : migrations) {
            migration.fillDummy();
        }
    }
}
