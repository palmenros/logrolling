package com.logrolling.server.database.migrations;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.services.authentication.TokenMigration;
import com.logrolling.server.services.chats.MessageMigration;
import com.logrolling.server.services.favors.FavorMigration;
import com.logrolling.server.services.gifts.GiftMigration;
import com.logrolling.server.services.users.UserMigration;

public class MigrationManager {

    //TODO: Maybe move each migration into each service?

    /**
     * List of migrations to execute.
     * Executed in declaration order
     */
    private static Migration[] migrations = new Migration[]{

            new UserMigration(),
            new FavorMigration(),
            new TokenMigration(),
            new GiftMigration(),
            new MessageMigration()
    };

    /**
     * A migration is a way to keep databases clean and testable between developers.
     * Every time the server is executed during development, all tables will be recreated
     * and filled with dummy data.
     */
    public synchronized static void migrate(boolean recreateTables) {

        //Only development code

        if(recreateTables) {
            //Delete all tables
            Database database = DatabaseFactory.createInstance();
            database.deleteAllTables();
            database.close();
        }

        for (Migration migration : migrations) {
            migration.migrate();
        }
    }

    public synchronized static void fillDummy() {

        for (Migration migration : migrations) {
            migration.fillDummy();
        }
    }
}
