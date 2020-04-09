package com.logrolling.server.database.migrations;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.database.managers.GiftsManager;
import com.logrolling.server.model.Gift;

public class GiftMigration implements Migration {

    @Override
    public void migrate() {

        //Create User Table
        String sqlQuery =
                "create table gifts (" +
                        "id int auto_increment," +
                        "title varchar(50) not null," +
                        "content varchar(150) not null," +
                        "price int," +
                        "constraint gifts_pk primary key (id)" +
                        ");";

        Database db = DatabaseFactory.createInstance();
        db.executeUpdate(sqlQuery);

        sqlQuery =
                "create table purchasedGifts (" +
                        "id int auto_increment," +
                        "title varchar(50) not null," +
                        "content varchar(150) not null," +
                        "user varchar(50) not null," +
                        "constraint purchasedGifts_pk primary key (id)" +
                        ");";


        db.executeUpdate(sqlQuery);

        db.close();

    }
    /**
     * Fills tables affected by this migration with dummy data
     */
    @Override
    public void fillDummy() {

        Gift[] giftsList = new Gift[] {
                new Gift("PlayStation", "Consola moderna", 500),
                new Gift("Ordenador", "El ordenador mas veloz del mercado", 1200)

        };

        for(Gift gift : giftsList) {
            GiftsManager.createGift(gift);
        }
    }
}
