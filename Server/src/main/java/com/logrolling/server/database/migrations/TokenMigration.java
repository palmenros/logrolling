package com.logrolling.server.database.migrations;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.database.managers.FavorManager;
import com.logrolling.server.database.managers.TokenManager;
import com.logrolling.server.model.Favor;
import com.logrolling.server.model.Token;

public class TokenMigration implements Migration {

    @Override
    public void migrate() {

        //Create User Table
        String sqlQuery =
                "create table tokens (" +
                        "id int auto_increment,"+
                        "content varchar(50) not null," +
                        "user varchar(50) not null,"+
                        "constraint tokens_pk primary key (id)" +
                        ");";

        Database db = DatabaseFactory.createInstance();
        db.executeUpdate(sqlQuery);

    }



    /**
     * Fills tables affected by this migration with dummy data
     */
    @Override
    public void fillDummy() {

        Token[] tokenlist = new Token[] {
                new Token("Hola", "Pablo"),
                new Token("Mundo", "Pedro")

        };

        for(Token token : tokenlist) {
            TokenManager.createToken(token);
        }
    }
}
