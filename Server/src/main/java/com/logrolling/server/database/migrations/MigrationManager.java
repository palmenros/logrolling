package com.logrolling.server.database.migrations;

public class MigrationManager {

    //TODO: Create migration abstract class and an array of migrations to call on migrate and fill

    public static void migrate() {

        //TODO: Implement migrations

        /*
            A migration is a way to keep databases clean and testable between developers.
            Every time the server is executed during development, all tables will be recreated
            and filled with dummy data.
         */

        //Delete all tables from database
        //Create tables
    }

    public static void fill() {
        //Fill databases with dummy data
    }
}
