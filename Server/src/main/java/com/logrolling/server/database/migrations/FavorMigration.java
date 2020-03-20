package com.logrolling.server.database.migrations;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.database.managers.FavorManager;
import com.logrolling.server.model.Favor;

public class FavorMigration implements Migration{

    /**
     * Creates tables necessary for model manager of given class
     */
    @Override
    public void migrate() {

        //Create User Table
        String sqlQuery =
                "create table favors (" +
                        "id int auto_increment,"+
                        "creator varchar(50) not null," +
                        "title varchar(50) not null," +
                        "description varchar(150) not null," +
                        "dueTime integer," +
                        "constraint users_pk primary key (id)" +
                        ");";

        Database db = DatabaseFactory.createInstance();
        db.executeUpdate(sqlQuery);
    }



    /**
     * Fills tables affected by this migration with dummy data
     */
    @Override
    public void fillDummy() {

        Favor[] favorList = new Favor[] {
                new Favor("Pedro", "Ir a la compra", "Necesito tomates y lechuga", 30),
                new Favor("Pablo", "Pasear al perro", "Darle un paseo por el campo", 60),

        };

        for(Favor favor : favorList) {
            FavorManager.createFavor(favor);
        }
    }
}
