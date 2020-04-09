package com.logrolling.server.database.managers;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.model.Gift;
import com.logrolling.server.model.Token;

public class GiftsManager {

    public static void createGift(Gift gift) {

        Database db = DatabaseFactory.createInstance();
        db.executeUpdate(
                "INSERT INTO gifts (title, content) VALUES (?, ?);",
                new String[]{
                        gift.getTitle(),
                        gift.getContent()
                });

        db.close();
    }
}
