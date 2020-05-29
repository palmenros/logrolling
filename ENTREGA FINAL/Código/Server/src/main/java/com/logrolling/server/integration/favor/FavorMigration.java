package com.logrolling.server.integration.favor;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.database.migrations.Migration;
import com.logrolling.server.integration.favor.FavorDAO;
import com.logrolling.server.services.favors.Favor;

import java.util.Date;

public class FavorMigration implements Migration {

    /**
     * Creates tables necessary for model manager of given class
     */
    @Override
    public void migrate() {

        //Create User Table
        String sqlQuery =
                "create table if not exists favors (" +
                        "id int auto_increment," +
                        "creator varchar(50) not null," +
                        "title varchar(50) not null," +
                        "description varchar(500) not null," +
                        "dueTime integer," +                    //Seconds from 1970
                        "reward int," +
                        "latitude double," +
                        "longitude double," +
                        "worker varchar(50)," +
                        "completed int," +
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

        Favor[] favorList = new Favor[]{
                new Favor("pedro", "Ir a la compra", "Necesito tomates y lechuga", (int) (new Date().getTime() / 1000L) + 3600 * 24, 100, 40.3861212, -3.9312154, false),
                new Favor("pablo", "Pasear al perro", "Darle un paseo por el campo", (int) (new Date().getTime() / 1000L) + 3600 * 48, 13, 40.388425, -3.941058, false),

        };

        for (Favor favor : favorList) {
            FavorDAO.createFavorUnchecked(favor);
        }
    }
}
