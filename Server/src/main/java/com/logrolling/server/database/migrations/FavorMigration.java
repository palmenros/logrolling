package com.logrolling.server.database.migrations;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.favors.FavorManager;
import com.logrolling.server.favors.Favor;

public class FavorMigration implements Migration{

    /**
     * Creates tables necessary for model manager of given class
     */
    @Override
    public void migrate() {

        //Create User Table
        String sqlQuery =
                "create table favors (" +
                        "id int auto_increment," +
                        "creator varchar(50) not null," +
                        "title varchar(50) not null," +
                        "description varchar(150) not null," +
                        "dueTime integer," +
                        "reward int," +
                        "latitude double," +
                        "longitude double," +
                        "worker varchar(50)," +
                        "constraint favors_pk primary key (id)" +
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
                new Favor("pedro", "Ir a la compra", "Necesito tomates y lechuga", 30, 100, 40.384408, 10),
                new Favor("pablo", "Pasear al perro", "Darle un paseo por el campo", 30, 13,40.388425, -3.941058),

        };

        for(Favor favor : favorList) {
            FavorManager.createFavorUnchecked(favor);
        }
    }
}
